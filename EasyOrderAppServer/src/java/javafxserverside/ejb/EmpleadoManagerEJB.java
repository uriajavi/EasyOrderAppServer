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
import javafxserverside.utils.MyEmail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		LOGGER.info("EmpleadoManagerEJB: Finding employee by id.");
		Empleado empleado = null;

		try {
			empleado = entityManager.find(Empleado.class, id);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception Finding employee by id:", ex.getMessage());
			throw new ReadException(ex.getMessage());
		}

		LOGGER.log(Level.INFO, "EmpleadoManagerEJB: Employee found {0}", empleado.getId());
		return empleado;
	}

	/**
	 * Signs in a {@link Empleado} by its login and password.
	 *
	 * @param login The login for the employee to be found.
	 * @param password The password for the employee to be found.
	 * @return The {@link Empleado} object containing employee data.
	 * @throws ReadException If there is any Exception during processing.
	 */
	@Override
	public Empleado iniciarSesion(String login, String password) throws ReadException {
		LOGGER.info("EmpleadoManagerEJB: Finding employee by login.");
		Empleado empleado = null;

		try {
			password = digestPassword(decryptPassword(password));

			empleado = (Empleado) entityManager.createNamedQuery("findEmployeeByLogin").setParameter("login", login).getSingleResult();
			if (empleado != null) {
				LOGGER.log(Level.INFO, "EmpleadoManagerEJB: Employee found {0}", empleado.getLogin());

				if (password.equals(empleado.getPassword())) {
					LOGGER.log(Level.INFO, "EmpleadoManagerEJB: Employee last access updating...");

					empleado.setLastAccess(new Date());
					entityManager.merge(empleado);
					entityManager.flush();

					LOGGER.log(Level.INFO, "EmpleadoManagerEJB: Employee last access updated.");
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


	/**
	 * Change the password of an {@link Empleado}.
	 *
	 * @param login The login for the employee to be found.
	 * @param actualPassword The current password of the employee.
	 * @param nuevaPassword The new password of the employee.
	 * @return The {@link Empleado} object containing employee data.
	 * @throws ReadException If there is any Exception during processing.
	 */
	@Override
	public Empleado cambiarContrasegna(String login, String actualPassword, String nuevaPassword) throws ReadException {
		LOGGER.info("EmpleadoManagerEJB: Changing password.");
		Empleado empleado = null;

		try {
			actualPassword = Crypto.digestPassword(Crypto.decryptPassword(actualPassword));
			nuevaPassword = Crypto.digestPassword(Crypto.decryptPassword(nuevaPassword));

			empleado = (Empleado) entityManager.createNamedQuery("findEmployeeByLogin").setParameter("login", login).getSingleResult();
			if (empleado != null) {
				LOGGER.info("EmpleadoManagerEJB: Employee found.");

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
					MyEmail.sendEmail(emailAccount, emailPassword, empleado.getEmail(), subject, message);
				} else {
					LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception wrong password");
					throw new UpdateException("Wrong password");
				}
			}
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception changing password, {0}.", ex.getMessage());
			throw new ReadException(ex.getMessage());
		}

		LOGGER.info("EmpleadoManagerEJB: Changed password.");
		return empleado;
	}

	/**
	 * Restores the password of an {@link Empleado}.
	 *
	 * @param login The login of the employee to be found.
	 * @return True if restored correctly, false otherwise.
	 * @throws ReadException If there is any Exception during processing.
	 */
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
				String nuevaPasswordDiggest = Crypto.digestPassword(nuevaPassword.getBytes());

				// Save emailPassword
				empleado.setPassword(nuevaPasswordDiggest);
				empleado.setLastPasswordChange(new Date());
				entityManager.merge(empleado);
				entityManager.flush();
				isPasswordRestored = true;

				// Send email
				String emailAccount = ResourceBundle.getBundle("javafxserverside.config.parameters").getString("email.account");
				emailAccount = Crypto.decryptSecretKey(emailAccount);
				String emailPassword = ResourceBundle.getBundle("javafxserverside.config.parameters").getString("email.password");
				emailPassword = Crypto.decryptSecretKey(emailPassword);
				String subject = "EasyOrderApp Password Restored";
				String message = "Your password has been restored to: " + nuevaPassword;
				MyEmail.sendEmail(emailAccount, emailPassword, empleado.getEmail(), subject, message);
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
		LOGGER.info("EmpleadoManagerEJB: Reading all employees.");
		List<Empleado> empleados = null;

		try {
			empleados = entityManager.createNamedQuery("findAllEmployees").getResultList();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception reading all employees: ", ex.getMessage());
			throw new ReadException(ex.getMessage());
		}

		LOGGER.info("EmpleadoManagerEJB: Read all employees.");
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
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception creating employee.{0}", ex.getMessage());
			throw new CreateException(ex.getMessage());
		}

		LOGGER.info("EmpleadoManagerEJB: Employee created.");
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
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception updating employee.{0}", ex.getMessage());
			throw new UpdateException(ex.getMessage());
		}

		LOGGER.info("EmpleadoManagerEJB: Employee updated.");
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
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoManagerEJB: Exception deleting employee.{0}", ex.getMessage());
			throw new DeleteException(ex.getMessage());
		}

		LOGGER.info("EmpleadoManagerEJB: Employee deleted.");
	}

}
