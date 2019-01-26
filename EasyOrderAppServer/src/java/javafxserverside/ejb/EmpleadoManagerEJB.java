package javafxserverside.ejb;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafxserverside.entity.Empleado;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxserverside.utils.Crypto;
import static javafxserverside.utils.Crypto.decryptPassword;
import static javafxserverside.utils.Crypto.digestPassword;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * EJB class for managing employee entity CRUD operations.
 *
 * @author Imanol
 */
@Stateless
public class EmpleadoManagerEJB implements EmpleadoManagerEJBLocal {

	/**
	 * Logger for the class.
	 */
	private static final Logger LOGGER = Logger.getLogger("easyorderappserver");
	/**
	 * Entity manager object.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Finds a {@link Empleado} by its id.
	 *
	 * @param id The id for the user to be found.
	 * @return The {@link Empleado} object containing employee data.
	 * @throws ReadException If there is any Exception during processing.
	 */
	@Override
	public Empleado findEmpleadoById(Integer id) throws ReadException {
		Empleado empleado = null;

		try {
			LOGGER.info("EmpleadoManagerEJB: Finding employee by id.");
			empleado = entityManager.find(Empleado.class, id);
			LOGGER.log(Level.INFO, "EmpleadoManagerEJB: Employee found {0}", empleado.getId());
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception Finding employee by id:", ex.getMessage());
			throw new ReadException(ex.getMessage());
		}

		return empleado;
	}

	@Override
	public Empleado iniciarSesion(String login, String password) throws ReadException {
		Empleado empleado = null;

		try {
			LOGGER.info("EmpleadoManagerEJB: Finding employee by login.");
			password = digestPassword(decryptPassword(password));

			empleado = (Empleado) entityManager.createNamedQuery("findEmployeeByLogin").setParameter("login", login).getSingleResult();
			if (empleado != null) {
				LOGGER.log(Level.INFO, "EmpleadoManagerEJB: Employee found {0}", empleado.getLogin());
				// compare passwords
				if (password.equals(empleado.getPassword())) {
					// Update last access 
					empleado.setLastAccess(new Date());
					entityManager.merge(empleado);
					entityManager.flush();
					LOGGER.log(Level.INFO, "EmpleadoManagerEJB: Employee last access update.");
				} else {
					LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception wrong password.");
					throw new ReadException("Wrong password");
				}

			}
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception Finding employee by login, {0}.", ex.getMessage());
			throw new ReadException(ex.getMessage());
		}

		return empleado;
	}

	private void sendEmail(String sender, String password, String receiver, String subject, String message) {
		LOGGER.info("EmpleadoManagerEJB: Sending email...");

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
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception sending email, {0}.", ex.getMessage());
		}

		LOGGER.info("EmpleadoManagerEJB: Sent email.");
	}

	@Override
	public Empleado cambiarContrasegna(String login, String actualPassword, String nuevaPassword) throws ReadException{
		Empleado empleado = null;

		try {
			LOGGER.info("EmpleadoManagerEJB: Changing password.");
			actualPassword = Crypto.digestPassword(Crypto.decryptPassword(actualPassword));
			nuevaPassword = Crypto.digestPassword(Crypto.decryptPassword(nuevaPassword));

			empleado = (Empleado) entityManager.createNamedQuery("findEmployeeByLogin").setParameter("login", login).getSingleResult();
			if (empleado != null) {
				if (actualPassword.equals(empleado.getPassword())) {
					empleado.setPassword(nuevaPassword);
					empleado.setLastPasswordChange(new Date());
					entityManager.merge(empleado);
					entityManager.flush();
					// Send email
					String emailAccount = ResourceBundle.getBundle("javafxserverside.config.parameters").getString("email.account");
					emailAccount = Crypto.decryptSecretKey(emailAccount);
					String emailPassword = ResourceBundle.getBundle("javafxserverside.config.parameters").getString("email.password");
					emailPassword = Crypto.decryptSecretKey(emailPassword);
					String subject = "EasyOrderApp Password Change";
					String message = "Your password has been changed.";
					sendEmail(emailAccount, emailPassword, empleado.getEmail(), subject, message);
					LOGGER.info("EmpleadoManagerEJB: Changed password.");
				} else {
					LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception wrong password");
					throw new UpdateException("Wrong password");
				}
			}
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception changing password, {0}.", ex.getMessage());
			throw new ReadException(ex.getMessage());
		}

		return empleado;
	}

	@Override
	public boolean recuperarContrasegna(String login) throws ReadException {
		LOGGER.info("EmpleadoManagerEJB: Restoring password...");
		boolean isPasswordRestored = false;
		try {

			Empleado empleado = (Empleado) entityManager.createNamedQuery("findEmployeeByLogin").setParameter("login", login).getSingleResult();
			if (empleado != null) {
				// Generate random emailPassword
				String nuevaPassword = Crypto.generateSecurePassword();

				// Diggest emailPassword
				//byte[] a = DatatypeConverter.parseHexBinary(nuevaPassword);
				String nuevaPasswordDiggest = Crypto.digestPassword(nuevaPassword.getBytes());

				// Save emailPassword
				empleado.setPassword(nuevaPasswordDiggest);
				empleado.setLastPasswordChange(new Date());
				entityManager.merge(empleado);
				entityManager.flush();
				isPasswordRestored = true;

				// Get emailAccount and send new emailPassword
				String emailAccount = ResourceBundle.getBundle("javafxserverside.config.parameters").getString("email.account");
				emailAccount = Crypto.decryptSecretKey(emailAccount);
				String emailPassword = ResourceBundle.getBundle("javafxserverside.config.parameters").getString("email.password");
				emailPassword = Crypto.decryptSecretKey(emailPassword);
				String subject = "EasyOrderApp Password Restored";
				String message = "Your password has been restored to: " + nuevaPassword;
				sendEmail(emailAccount, emailPassword, empleado.getEmail(), subject, message);
			}
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception restoring password, {0}.", ex.getMessage());
			throw new ReadException(ex.getMessage());
		}
		LOGGER.info("EmpleadoManagerEJB: Restored password.");
		return isPasswordRestored;
	}

	/**
	 * Finds a List of {@link Empleado} objects containing data for all
	 * employees in the application data storage.
	 *
	 * @return A List of {@link Empleado} objects.
	 * @throws ReadException If there is any Exception during processing.
	 */
	@Override
	public List<Empleado> findAllEmpleados() throws ReadException {
		List<Empleado> empleados = null;
		try {
			LOGGER.info("EmpleadoManagerEJB: Reading all employees.");
			empleados = entityManager.createNamedQuery("findAllEmployees").getResultList();
			LOGGER.info("EmpleadoManagerEJB: Read all employees.");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception reading all employees: ", ex.getMessage());
			throw new ReadException(ex.getMessage());
		}
		return empleados;
	}

	/**
	 * Creates an employee and stores it in the underlying application storage.
	 *
	 * @param empleado The {@link Empleado} object containing the user data.
	 * @throws CreateException If there is any Exception during processing.
	 */
	@Override
	public void createEmpleado(Empleado empleado) throws CreateException {
		LOGGER.info("EmpleadoManagerEJB: Creating employee.");
		try {
			empleado.setPassword(digestPassword(decryptPassword(empleado.getPassword())));
			entityManager.persist(empleado);
			LOGGER.info("EmpleadoManagerEJB: Employee created.");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception creating employee.{0}", ex.getMessage());
			throw new CreateException(ex.getMessage());
		}
	}

	/**
	 * Updates an employee's data in the underlying application storage.
	 *
	 * @param empleado The {@link Empleado} object containing the employee data.
	 * @throws UpdateException If there is any Exception during processing.
	 */
	@Override
	public void updateEmpleado(Empleado empleado) throws UpdateException {
		LOGGER.info("EmpleadoManagerEJB: Updating employee.");
		try {
			entityManager.merge(empleado);
			entityManager.flush();
			LOGGER.info("EmpleadoManagerEJB: Employee updated.");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception updating employee.{0}", ex.getMessage());
			throw new UpdateException(ex.getMessage());
		}
	}

	/**
	 * Deletes an employee's data in the underlying application storage.
	 *
	 * @param empleado The {@link Empleado} object containing the employee data.
	 * @throws DeleteException If there is any Exception during processing.
	 */
	@Override
	public void deleteEmpleado(Empleado empleado) throws DeleteException {
		LOGGER.info("EmpleadoManagerEJB: Deleting employee.");
		try {
			empleado = entityManager.merge(empleado);
			entityManager.remove(empleado);
			LOGGER.info("EmpleadoManagerEJB: Employee deleted.");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception deleting employee.{0}", ex.getMessage());
			throw new DeleteException(ex.getMessage());
		}
	}

}
