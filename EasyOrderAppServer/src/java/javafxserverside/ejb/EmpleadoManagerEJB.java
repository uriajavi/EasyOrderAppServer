package javafxserverside.ejb;

import java.util.List;
import javafxserverside.entity.Empleado;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
