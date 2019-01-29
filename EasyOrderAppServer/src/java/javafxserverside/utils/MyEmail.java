package javafxserverside.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Custom class for email related code.
 * @author Imanol
 */
public class MyEmail {
	
	/**
	 * Logger for the class.
	 */
	private static final Logger LOGGER = Logger.getLogger("easyorderappserver");
	/**
	 * Sends an email with the input data.
	 *
	 * @param sender Email for the sender.
	 * @param password Password for the sender.
	 * @param receiver Email for the receiver.
	 * @param subject Subject for the email.
	 * @param message Content message for the email.
	 */
	public static void sendEmail(String sender, String password, String receiver, String subject, String message) {
		LOGGER.info("MyEmail: Sending email...");

		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(587);
			email.setSslSmtpPort("587");
			email.setAuthenticator(new DefaultAuthenticator(sender, password));
			email.setSSLOnConnect(true);
			email.setFrom(sender);
			email.setSubject(subject);
			email.setMsg(message);
			email.addTo(receiver);
			email.setStartTLSEnabled(true);
			email.send();
		} catch (EmailException ex) {
			LOGGER.log(Level.SEVERE, "MyEmail: Exception sending email, {0}.", ex.getMessage());
		}

		LOGGER.info("MyEmail: Sent email.");
	}
}
