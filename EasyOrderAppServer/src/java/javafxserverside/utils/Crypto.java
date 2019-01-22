/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
}
