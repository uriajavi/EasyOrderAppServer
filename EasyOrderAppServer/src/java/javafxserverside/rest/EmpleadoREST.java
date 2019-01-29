package javafxserverside.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxserverside.ejb.EmpleadoManagerEJBLocal;
import javafxserverside.entity.Empleado;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.InternalServerErrorException;

/**
 * RESTful web service class exposing CRUD operations for {@link Empleado}
 * entities.
 *
 * @author Imanol
 */
@Path("empleado")
public class EmpleadoREST {

	/**
	 * Logger for class methods.
	 */
	private static final Logger LOGGER = Logger.getLogger("easyorderappserver");

	/**
	 * EJB reference for business logic object.
	 */
	@EJB
	private EmpleadoManagerEJBLocal ejb;

	/**
	 * RESTful GET method for reading {@link Empleado} objects through an XML
	 * representation.
	 *
	 * @param id The id for the object to read.
	 * @return The Empleado object containing data.
	 */
	@GET
	@Path("{id}")
	@Produces({"application/xml"})
	public Empleado find(@PathParam("id") Integer id) {
		LOGGER.log(Level.INFO, "EmpleadoRESTful service: find employee by id={0}", id);
		Empleado empleado = null;

		try {
			empleado = ejb.findEmpleadoById(id);
		} catch (ReadException ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoRESTful service: Exception reading employee by id, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}

		LOGGER.log(Level.INFO, "EmpleadoRESTful service: found employee by id={0}", id);
		return empleado;
	}

	/**
	 * RESTful GET method for reading {@link Empleado} objects through an XML
	 * representation.
	 *
	 * @param login The login for the object to read.
	 * @param password The password for the object to read.
	 * @return The Empleado object containing data.
	 */
	@GET
	@Path("login/{login}/{password}")
	@Produces({"application/xml"})
	public Empleado iniciarSesion(@PathParam("login") String login, @PathParam("password") String password) {
		LOGGER.log(Level.INFO, "EmpleadoRESTful service: find employee by login={0}", login);
		Empleado empleado = null;

		try {
			empleado = ejb.iniciarSesion(login, password);
		} catch (ReadException ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoRESTful service: Exception reading employee by login, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}

		LOGGER.log(Level.INFO, "EmpleadoRESTful service: found employee by login={0}", login);
		return empleado;
	}

	/**
	 * RESTful GET method for changing the password of an {@link Empleado}
	 * object.
	 *
	 * @param login The login for the object to read.
	 * @param actualPassword The current password of the object.
	 * @param nuevaPassword The new password of the object.
	 * @return The Empleado object containing the new data.
	 */
	@GET
	@Path("login/{login}/{actualPassword}/{nuevaPassword}")
	@Produces({"application/xml"})
	public Empleado cambiarContrasegna(@PathParam("login") String login, @PathParam("actualPassword") String actualPassword, @PathParam("nuevaPassword") String nuevaPassword) {
		LOGGER.log(Level.INFO, "EmpleadoRESTful service: change password employee by login={0}", login);
		Empleado empleado = null;

		try {
			empleado = ejb.cambiarContrasegna(login, actualPassword, nuevaPassword);
		} catch (ReadException ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoRESTful service: Exception changing password, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}

		LOGGER.log(Level.INFO, "EmpleadoRESTful service: changed password employee by login={0}", login);
		return empleado;
	}

	/**
	 * RESTful GET method for restoring the password of an {@link Empleado}
	 * object.
	 *
	 * @param login The login for the object to read.
	 * @return True if change was made correctly, False otherwise.
	 */
	@GET
	@Path("login/{login}")
	@Produces({"application/xml"})
	public boolean recuperarContrasegna(@PathParam("login") String login) {
		LOGGER.log(Level.INFO, "EmpleadoRESTful service: restoring password...");
		boolean isPasswordRestored = false;

		try {
			isPasswordRestored = ejb.recuperarContrasegna(login);
		} catch (ReadException ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoRESTful service: Exception restoring password, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}

		LOGGER.log(Level.INFO, "EmpleadoRESTful service: restored password.");
		return isPasswordRestored;
	}

	/**
	 * RESTful GET method for reading all {@link Empleado} objects through an
	 * XML representation.
	 *
	 * @return A List of Empleado objects containing data.
	 */
	@GET
	@Produces({"application/xml"})
	public List<Empleado> findAll() {
		LOGGER.log(Level.INFO, "EmpleadoRESTful service: find all employees.");
		List<Empleado> empleados = null;

		try {
			empleados = ejb.findAllEmpleados();
		} catch (ReadException ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoRESTful service: Exception reading all employees, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}

		LOGGER.log(Level.INFO, "EmpleadoRESTful service: found all employees.");
		return empleados;
	}

	/**
	 * RESTful POST method for creating {@link Empleado} objects from XML
	 * representation.
	 *
	 * @param empleado The object containing employee data.
	 */
	@POST
	@Consumes({"application/xml"})
	public void create(Empleado empleado) {
		LOGGER.log(Level.INFO, "EmpleadoRESTful service: create {0}.", empleado.getLogin());

		try {
			empleado.setId(null); // POST method doesn't allow id value
			ejb.createEmpleado(empleado);
		} catch (CreateException ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoRESTful service: Exception creating employee, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}

		LOGGER.log(Level.INFO, "EmpleadoRESTful service: created {0}.", empleado.getLogin());
	}

	/**
	 * RESTful PUT method for updating {@link Empleado} objects from XML
	 * representation.
	 *
	 * @param empleado The object containing employee data.
	 */
	@PUT
	@Consumes({"application/xml"})
	public void update(Empleado empleado) {
		LOGGER.log(Level.INFO, "EmpleadoRESTful service: update {0}.", empleado.getLogin());

		try {
			ejb.updateEmpleado(empleado);
		} catch (UpdateException ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoRESTful service: Exception updating employee, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}

		LOGGER.log(Level.INFO, "EmpleadoRESTful service: updated {0}.", empleado.getLogin());
	}

	/**
	 * RESTful DELETE method for deleting {@link Empleado} objects from id.
	 *
	 * @param id The id for the object to be deleted.
	 */
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		LOGGER.log(Level.INFO, "EmpleadoRESTful service: delete Empleado by id={0}", id);

		try {
			ejb.deleteEmpleado(ejb.findEmpleadoById(id));
		} catch (ReadException | DeleteException ex) {
			LOGGER.log(Level.SEVERE, "EmpleadoRESTful service: Exception deleting employee by id, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}

		LOGGER.log(Level.INFO, "EmpleadoRESTful service: deleted Empleado by id={0}", id);
	}
}
