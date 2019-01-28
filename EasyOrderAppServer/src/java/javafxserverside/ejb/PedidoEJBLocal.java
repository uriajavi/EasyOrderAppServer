/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.ejb;

import java.util.List;
import javafxserverside.entity.Cliente;
import javafxserverside.entity.Pedido;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import javax.ejb.Local;

/**
 * EJB Local Interface for managing Pedido entity CRUD operations.
 *
 * @author Leticia Garcia
 */
@Local
public interface PedidoEJBLocal {

    /**
     * Finds a {@link Pedido} by its id.
     *
     * @param id The id for the order to be found.
     * @return The {@link Pedido} object containing pedido data.
     * @throws ReadException If there is any Exception during processing.
     */
    public Pedido buscarPedidoPorId(Integer id) throws ReadException;

    /**
     * Finds a List of {@link Pedido} objects containing data for all pedido in
     * the application data storage.
     *
     * @return A List of {@link Pedido} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Pedido> buscarTodosLosPedidos() throws ReadException;

    /**
     * Creates a pedido and stores it in the underlying application storage.
     *
     * @param pedido The {@link Pedido} object containing the pedido data.
     * @throws CreateException If there is any Exception during processing.
     */
    public void crearPedido(Pedido pedido) throws CreateException;

    /**
     * Updates a pedido's data in the underlying application storage.
     *
     * @param pedido The {@link Pedido} object containing the pedido data.
     * @throws UpdateException If there is any Exception during processing.
     */
    public void actualizarEstado(Pedido pedido) throws UpdateException;

    /**
     * Deletes a pedido's data in the underlying application storage.
     *
     * @param pedido The {@link Pedido} object containing the user data.
     * @throws DeleteException If there is any Exception during processing.
     */
    public void eliminarPedido(Pedido pedido) throws DeleteException;

    /**
     * Finds a List of {@link Pedido} objects containing data for all pedidos
     * with certain id cliene value.
     *
     * @param cliente The cliente value for the pedidos to be found.
     * @return A List of {@link Pedido} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Pedido> buscarPedidoDeCliente(Cliente cliente) throws ReadException;

}
