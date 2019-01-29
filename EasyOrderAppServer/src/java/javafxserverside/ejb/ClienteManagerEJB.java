package javafxserverside.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxserverside.entity.Cliente;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB class for managing client entity CRUD operations.
 *
 * @author Imanol
 */
@Stateless
public class ClienteManagerEJB implements ClienteManagerEJBLocal {

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
	 * Finds a {@link Cliente} by its id.
	 *
	 * @param id The id for the client to be found.
	 * @return The {@link Cliente} object containing client data.
	 * @throws ReadException If there is any Exception during processing.
	 */
	@Override
	public Cliente findClienteById(Integer id) throws ReadException {
		Cliente cliente = null;

		try {
			LOGGER.info("ClienteManagerEJB: Finding client by id.");
			cliente = entityManager.find(Cliente.class, id);
			LOGGER.log(Level.INFO, "ClienteManagerEJB: Client found {0}.", cliente.getId());
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "ClienteManagerEJB: Exception Finding client by id:", ex.getMessage());
			throw new ReadException(ex.getMessage());
		}
		return cliente;
	}

	/**
	 * Finds a List of {@link Cliente} objects containing data for all clients
	 * in the application data storage.
	 *
	 * @return A List of {@link Cliente} objects.
	 * @throws ReadException If there is any Exception during processing.
	 */
	@Override
	public List<Cliente> findAllClientes() throws ReadException {
		List<Cliente> clientes = null;
		try {
			LOGGER.info("ClienteManagerEJB: Reading all clients.");
			clientes = entityManager.createNamedQuery("findAllClients").getResultList();
			LOGGER.info("ClienteManagerEJB: Read all clients.");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "ClienteManagerEJB: Exception reading all clients: ", ex.getMessage());
			throw new ReadException(ex.getMessage());
		}
		return clientes;
	}

	/**
	 * Creates a client and stores it in the underlying application storage.
	 *
	 * @param cliente The {@link Cliente} object containing the client data.
	 * @throws CreateException If there is any Exception during processing.
	 */
	@Override
	public void createCliente(Cliente cliente) throws CreateException {
		LOGGER.info("ClienteManagerEJB: Creating client.");
		try {
			entityManager.persist(cliente);
			LOGGER.info("ClienteManagerEJB: Client created.");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "ClienteManagerEJB: Exception creating client.{0}", ex.getMessage());
			throw new CreateException(ex.getMessage());
		}
	}

	/**
	 * Updates a client's data in the underlying application storage.
	 *
	 * @param cliente The {@link Cliente} object containing the employee data.
	 * @throws UpdateException If there is any Exception during processing.
	 */
	@Override
	public void updateCliente(Cliente cliente) throws UpdateException {
		LOGGER.info("ClienteManagerEJB: Updating client.");
		try {
			entityManager.merge(cliente);
			entityManager.flush();
			LOGGER.info("ClienteManagerEJB: Client updated.");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "ClienteManagerEJB: Exception updating client.{0}", ex.getMessage());
			throw new UpdateException(ex.getMessage());
		}
	}

	/**
	 * Deletes a client's data in the underlying application storage.
	 *
	 * @param cliente The {@link Cliente} object containing the client data.
	 * @throws DeleteException If there is any Exception during processing.
	 */
	@Override
	public void deleteCliente(Cliente cliente) throws DeleteException {
		LOGGER.info("ClienteManagerEJB: Deleting client.");
		try {
			cliente = entityManager.merge(cliente);
			entityManager.remove(cliente);
			LOGGER.info("ClienteManagerEJB: Client deleted.");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "ClienteManagerEJB: Exception deleting client.{0}", ex.getMessage());
			throw new DeleteException(ex.getMessage());
		}
	}

}
