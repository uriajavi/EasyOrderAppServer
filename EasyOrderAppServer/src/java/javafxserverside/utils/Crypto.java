package javafxserverside.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Imanol
 */
public class Crypto {

	private static final Logger LOGGER = Logger.getLogger("easyorderappserver");

	public static byte[] decryptPassword(String password) {
		LOGGER.log(Level.INFO, "Crypto: decrypting password.");
		byte[] decryptedPassword = null;
		byte[] passwordByteArray = DatatypeConverter.parseHexBinary(password);

		FileInputStream fileInputStream = null;
		try {
			/*
			 * The first thing to do is to read the file that contains the encoded message.
			 * Then, we have to read (as a byte[]) the key with which the text is going to
			 * be deciphered, the private key.
			 */
			fileInputStream = new FileInputStream("private.key");
			byte[] inputPrivateKey = new byte[fileInputStream.available()];
			fileInputStream.read(inputPrivateKey);

			/*
			 * After that, you have to create a PrivateKey object. This object is going to be
			 * created using the method generatePrivate() from the class KeyFactory and using 
			 * the specification PKCS8EncodedKeySpec.
			 */
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(inputPrivateKey);
			PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);

			/*
			 * The last step is to decrypt the message using a Cipher object, specifying which 
			 * algorithm is going to be used. This object must be initialized in decrypt mode 
			 * using the previously created key.
			 */
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			decryptedPassword = cipher.doFinal(passwordByteArray);

		} catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
			LOGGER.log(Level.SEVERE, "Crypto: Exception decrypting password, {0}", ex.getMessage());
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException ex) {
				// Error closing input stream file
			}
		}

		LOGGER.log(Level.INFO, "Crypto: password decrypted.");
		return decryptedPassword;
	}

	public static String digestPassword(byte[] password) {
		LOGGER.log(Level.INFO, "Crypto: Digesting password.");
		String digestedPassword = null;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.update(password);
			digestedPassword = DatatypeConverter.printHexBinary(messageDigest.digest());

		} catch (NoSuchAlgorithmException ex) {
			LOGGER.log(Level.SEVERE, "Crypto: Exception digesting password, {0}", ex.getMessage());
		}
		LOGGER.log(Level.INFO, "Crypto: password digested.");

		return digestedPassword;
	}

	public static String decryptSecretKey(String path) {
		LOGGER.info("Crypto: Decrypting with secret key...");
		String decryptedText = null;
		String secretkey = ResourceBundle.getBundle("javafxserverside.config.parameters").getString("secret.key");

		int iterationCount = 6500;
		String sfkAlgorithm = "PBKDF2WithHmacSHA1";
		byte[] salt = "Hello world!!!!!".getBytes();
		KeySpec spec = new PBEKeySpec(secretkey.toCharArray(), salt, iterationCount, 128);

		ObjectInputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectInputStream(new FileInputStream(path));

			SecretKeyFactory factory = SecretKeyFactory.getInstance(sfkAlgorithm);
			byte[] key = factory.generateSecret(spec).getEncoded();
			SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

			byte[] iv = (byte[]) objectOutputStream.readObject();
			byte[] encodedMessage = (byte[]) objectOutputStream.readObject();

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivParam = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
			byte[] decodedMessage = cipher.doFinal(encodedMessage);
			decryptedText = new String(decodedMessage);

		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException | ClassNotFoundException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
			Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
		}
		LOGGER.info("Crypto: Decrypted with secret key.");
		return decryptedText;
	}

	public static String generateSecurePassword() {
		LOGGER.info("EmpleadoManagerEJB: Generating secure password...");
		String securePassword = null;
		String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

		int length = 10;

		try {
			Random random = SecureRandom.getInstanceStrong();
			StringBuilder stringBuilder = new StringBuilder(length);
			for (int i = 0; i < length; i++) {
				int indexRandom = random.nextInt(symbols.length);
				stringBuilder.append(symbols[indexRandom]);
			}
			securePassword = stringBuilder.toString();
		} catch (NoSuchAlgorithmException ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception generating secure password, {0}.", ex.getMessage());
			// TODO
		}

		LOGGER.info("EmpleadoManagerEJB: Generated secure password.");
		return securePassword;
	}
}
