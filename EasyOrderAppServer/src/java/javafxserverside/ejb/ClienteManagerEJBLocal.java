package javafxserverside.ejb;

import java.util.List;
import javafxserverside.entity.Cliente;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import javax.ejb.Local;

/**
 * EJB class for managing Cliente entity CRUD operations.
 *
 * @author Imanol
 */
@Local
public interface ClienteManagerEJBLocal {

	/**
	 * Finds a {@link Cliente} by its id.
	 *
	 * @param id The id for the client to be found.
	 * @return The {@link Cliente} object containing employee data.
	 * @throws ReadException If there is any Exception during processing.
	 */
	public Cliente findClienteById(Integer id) throws ReadException;

	/**
	 * Finds a List of {@link Cliente} objects containing data for all clients
	 * in the application data storage.
	 *
	 * @return A List of {@link Cliente} objects.
	 * @throws ReadException If there is any Exception during processing.
	 */
	public List<Cliente> findAllClientes() throws ReadException;

	/**
	 * Creates a client and stores it in the underlying application storage.
	 *
	 * @param cliente The {@link Cliente} object containing the client data.
	 * @throws CreateException If there is any Exception during processing.
	 */
	public void createCliente(Cliente cliente) throws CreateException;

	/**
	 * Updates an client's data in the underlying application storage.
	 *
	 * @param cliente The {@link Cliente} object containing the client data.
	 * @throws UpdateException If there is any Exception during processing.
	 */
	public void updateCliente(Cliente cliente) throws UpdateException;

	/**
	 * Deletes an client's data in the underlying application storage.
	 *
	 * @param cliente The {@link Cliente} object containing the client data.
	 * @throws DeleteException If there is any Exception during processing.
	 */
	public void deleteCliente(Cliente cliente) throws DeleteException;

}
