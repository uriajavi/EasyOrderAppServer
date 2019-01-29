package javafxserverside.ejb;

import java.util.List;
import javafxserverside.entity.Empleado;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import javax.ejb.Local;

/**
 * EJB class for managing Empleado entity CRUD operations.
 *
 * @author Imanol
 */
@Local
public interface EmpleadoManagerEJBLocal {

	/**
	 * Finds a {@link Empleado} by its id.
	 *
	 * @param id The id for the employee to be found.
	 * @return The {@link Empleado} object containing employee data.
	 * @throws ReadException If there is any Exception during processing.
	 */
	public Empleado findEmpleadoById(Integer id) throws ReadException;

	/**
	 * Signs in a {@link Empleado} by its login and password.
	 *
	 * @param login The login for the employee to be found.
	 * @param password The password for the employee to be found.
	 * @return The {@link Empleado} object containing employee data.
	 * @throws ReadException If there is any Exception during processing.
	 */
	public Empleado iniciarSesion(String login, String password) throws ReadException;

	/**
	 * Change the password of an {@link Empleado}.
	 *
	 * @param login The login for the employee to be found.
	 * @param actualPassword The current password of the employee.
	 * @param nuevaPassword The new password of the employee.
	 * @return The {@link Empleado} object containing employee data.
	 * @throws ReadException If there is any Exception during processing.
	 */
	public Empleado cambiarContrasegna(String login, String actualPassword, String nuevaPassword) throws ReadException;

	/**
	 * Restores the password of an {@link Empleado}.
	 *
	 * @param login The login of the employee to be found.
	 * @return True if restored correctly, false otherwise.
	 * @throws ReadException If there is any Exception during processing.
	 */
	public boolean recuperarContrasegna(String login) throws ReadException;

	/**
	 * Finds a List of {@link Empleado} objects containing data for all
	 * employees in the application data storage.
	 *
	 * @return A List of {@link Empleado} objects.
	 * @throws ReadException If there is any Exception during processing.
	 */
	public List<Empleado> findAllEmpleados() throws ReadException;

	/**
	 * Creates an employee and stores it in the underlying application storage.
	 *
	 * @param empleado The {@link Empleado} object containing the employee data.
	 * @throws CreateException If there is any Exception during processing.
	 */
	public void createEmpleado(Empleado empleado) throws CreateException;

	/**
	 * Updates an employee's data in the underlying application storage.
	 *
	 * @param empleado The {@link Empleado} object containing the employee data.
	 * @throws UpdateException If there is any Exception during processing.
	 */
	public void updateEmpleado(Empleado empleado) throws UpdateException;

	/**
	 * Deletes an employee's data in the underlying application storage.
	 *
	 * @param empleado The {@link Empleado} object containing the employee data.
	 * @throws DeleteException If there is any Exception during processing.
	 */
	public void deleteEmpleado(Empleado empleado) throws DeleteException;

}
