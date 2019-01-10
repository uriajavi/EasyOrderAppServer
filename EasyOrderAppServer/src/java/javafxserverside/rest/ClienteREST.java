package javafxserverside.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxserverside.ejb.ClienteManagerEJBLocal;
import javafxserverside.entity.Cliente;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * RESTful web service class exposing CRUD operations for {@link Cliente}
 * entities.
 *
 * @author Imanol
 */
@Path("cliente")
public class ClienteREST {

	/**
	 * Logger for class methods.
	 */
	private static final Logger LOGGER = Logger.getLogger("easyorderappserver");

	/**
	 * EJB reference for business logic object.
	 */
	@EJB
	private ClienteManagerEJBLocal ejb;

	/**
	 * RESTful GET method for reading {@link Cliente} objects through an XML
	 * representation.
	 *
	 * @param id The id for the object to read.
	 * @return The Cliente object containing data.
	 */
	@GET
	@Path("{id}")
	@Produces({"application/xml"})
	public Cliente find(@PathParam("id") Integer id) {
		Cliente cliente = null;

		try {
			LOGGER.log(Level.INFO, "ClienteRESTful service: find client by id={0}", id);
			cliente = ejb.findClienteById(id);
			LOGGER.log(Level.INFO, "ClienteRESTful service: found client by id={0}", id);
		} catch (ReadException ex) {
			LOGGER.log(Level.SEVERE, "ClienteRESTful service: Exception reading client by id, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}
		return cliente;
	}

	/**
	 * RESTful GET method for reading all {@link Cliente} objects through an XML
	 * representation.
	 *
	 * @return A List of Cliente objects containing data.
	 */
	@GET
	@Produces({"application/xml"})
	public List<Cliente> findAll() {
		List<Cliente> clientes = null;

		try {
			LOGGER.log(Level.INFO, "ClienteRESTful service: find all clients.");
			clientes = ejb.findAllClientes();
			LOGGER.log(Level.INFO, "ClienteRESTful service: found all clients.");
		} catch (ReadException ex) {
			LOGGER.log(Level.SEVERE, "ClienteRESTful service: Exception reading all clients, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}

		return clientes;
	}

	/**
	 * RESTful POST method for creating {@link Cliente} objects from XML
	 * representation.
	 *
	 * @param cliente The object containing client data.
	 */
	@POST
	@Consumes({"application/xml"})
	public void create(Cliente cliente) {
		try {
			LOGGER.log(Level.INFO, "ClienteRESTful service: create {0}.", cliente);
			ejb.createCliente(cliente);
			LOGGER.log(Level.INFO, "ClienteRESTful service: created {0}.", cliente);
		} catch (CreateException ex) {
			LOGGER.log(Level.SEVERE, "ClienteRESTful service: Exception creating client, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}
	}

	/**
	 * RESTful PUT method for updating {@link Cliente} objects from XML
	 * representation.
	 *
	 * @param cliente The object containing client data.
	 */
	@PUT
	@Consumes({"application/xml"})
	public void update(Cliente cliente) {
		try {
			LOGGER.log(Level.INFO, "ClienteRESTful service: update {0}.", cliente);
			ejb.updateCliente(cliente);
			LOGGER.log(Level.INFO, "ClienteRESTful service: updated {0}.", cliente);
		} catch (UpdateException ex) {
			LOGGER.log(Level.SEVERE, "ClienteRESTful service: Exception updating client, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}
	}

	/**
	 * RESTful DELETE method for deleting {@link Cliente} objects from id.
	 *
	 * @param id The id for the object to be deleted.
	 */
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		try {
			LOGGER.log(Level.INFO, "ClienteRESTful service: delete Cliente by id={0}", id);
			ejb.deleteCliente(ejb.findClienteById(id));
			LOGGER.log(Level.INFO, "ClienteRESTful service: deleted Cliente by id={0}", id);
		} catch (ReadException | DeleteException ex) {
			LOGGER.log(Level.SEVERE, "ClienteRESTful service: Exception deleting client by id, {0}", ex.getMessage());
			throw new InternalServerErrorException(ex);
		}
	}

}
