/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxserverside.entity.Cliente;
import javafxserverside.entity.Pedido;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB class for managing Pedido entity CRUD operations.
 *
 * @author Leticia Garcia
 */
@Stateless
public class PedidoEJB implements PedidoEJBLocal {

    /**
     * Logger for the class.
     */
    private static final Logger LOGGER
            = Logger.getLogger("javafxserverside");
    /**
     * Entity manager object.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Finds a {@link Pedido} by its id.
     *
     * @param id The id for the pedido to be found.
     * @return The {@link Pedido} object containing pedido data.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Pedido buscarPedidoPorId(Integer id) throws ReadException {

        Pedido pedido = null;
        try {
            LOGGER.info("Pedido: Finding pedido by id.");
            pedido = em.find(Pedido.class, id);
            if (pedido != null) {
                LOGGER.log(Level.INFO, "Pedido: Pedido found {0}", pedido.getId());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Pedido: Exception Finding pedido by id:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return pedido;
    }

    /**
     * Finds a List of {@link Pedido} objects containing data for all pedidos in
     * the application data storage.
     *
     * @return A List of {@link Pedido} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Pedido> buscarTodosLosPedidos() throws ReadException {
        List<Pedido> pedidos = null;
        try {
            LOGGER.info("Pedido: Reading all pedidos.");
            pedidos = em.createNamedQuery("buscarTodosLosPedidos").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Pedido: Exception reading all pedidos:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return pedidos;
    }

    /**
     * Creates a Pedido and stores it in the underlying application storage.
     *
     * @param pedido The {@link Pedido} object containing the pedido data.
     * @throws CreateException If there is any Exception during processing.
     */
    @Override
    public void crearPedido(Pedido pedido) throws CreateException {
        LOGGER.info("Pedido: Creating pedido.");
        try {
            em.persist(pedido);
            LOGGER.info("Pedido: Pedido created.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Pedido: Exception creating pedido.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Updates a pedido's data in the underlying application storage.
     *
     * @param pedido The {@link Pedido} object containing the user data.
     * @throws UpdateException If there is any Exception during processing.
     */
    @Override
    public void actualizarEstado(Pedido pedido) throws UpdateException {
        LOGGER.info("Pedido: Updating pedido.");
        try {

            em.merge(pedido);
            em.flush();
            LOGGER.info("Pedido: Pedido updated.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Pedido: Exception updating pedido.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Deletes a pedido's data in the underlying application storage.
     *
     * @param pedido The {@link Pedido} object containing the pedido data.
     * @throws DeleteException If there is any Exception during processing.
     */
    @Override
    public void eliminarPedido(Pedido pedido) throws DeleteException {
        LOGGER.info("Pedido: Deleting pedido.");
        try {

            //Antes de borrarlo aseguramos que el objeto existe
            if (!em.contains(pedido)) {
                pedido = em.merge(pedido);//Asegura que el objeto existe antes de borrarlo
            }            //Borra el pedido
            em.remove(pedido);
            LOGGER.info("Pedido: Pedido deleted.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Pedido: Exception deleting pedido.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Finds a List of {@link Pedido} objects containing data for all pedidos
     * with certain cliente value.
     *
     * @param cliente The cliente value for the pedidos to be found.
     * @return A List of {@link Pedido} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Pedido> buscarPedidoDeCliente(Cliente cliente) throws ReadException {
        List<Pedido> pedidos = null;
        try {
            LOGGER.info("Pedido: Reading pedidos by cliente.");
            pedidos = em.createNamedQuery("buscarPedidoDeCliente")
                    .setParameter("cliente", cliente)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Pedido: Exception reading pedido by cliente.",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return pedidos;
    }

}
