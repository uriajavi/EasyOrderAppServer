/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxserverside.ejb.PedidoEJBLocal;
import javafxserverside.entity.Cliente;
import javafxserverside.entity.Pedido;
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
 * RESTful web service class exposing CRUD operations for {@link Pedido}
 * entities.
 *
 * @author Leticia Garcia
 */
@Path("pedido")
public class PedidoFacadeREST {

    /**
     * Logger for class methods.
     */
    private static final Logger LOGGER
            = Logger.getLogger("javafxserverside");

    /**
     * EJB reference for business logic object.
     */
    @EJB
    private PedidoEJBLocal ejb;

    /**
     * RESTful POST method for creating {@link Pedido} objects from XML
     * representation.
     *
     * @param pedido The object containing pedido data.
     */
    @POST
    @Consumes({"application/xml"})

    public void create(Pedido pedido) {
        try {
            LOGGER.log(Level.INFO, "PedidoRESTful service: create {0}.", pedido);
            ejb.crearPedido(pedido);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE,
                    "PedidoRESTful service: Exception creating pedido, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * RESTful PUT method for updating {@link Pedido} objects from XML
     * representation.
     *
     * @param pedido The object containing pedido data.
     */
    @PUT
    @Consumes({"application/xml"})
    public void update(Pedido pedido) {
        try {
            LOGGER.log(Level.INFO, "PedidoRESTful service: update {0}.", pedido);
            ejb.actualizarEstado(pedido);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "PedidoRESTful service: Exception updating pedido, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * RESTful DELETE method for deleting {@link Pedido} objects from id.
     *
     * @param id The id for the object to be deleted.
     */
    @DELETE
    @Path("{id}")
    //@Consumes({"application/xml", "application/json"})
    public void delete(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "PedidoRESTful service: delete pedido by id={0}.", id);
            ejb.eliminarPedido(ejb.buscarPedidoPorId(id));
        } catch (ReadException | DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "PedidoRESTful service: Exception deleting pedido by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * RESTful GET method for reading {@link Pedido} objects through an XML
     * representation.
     *
     * @param id The id for the object to be read.
     * @return The pedido object containing data.
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml"})
    public Pedido find(@PathParam("id") Integer id) {
        Pedido pedido = null;
        try {
            LOGGER.log(Level.INFO, "PedidoRESTful service: find Pedido by id={0}.", id);
            pedido = ejb.buscarPedidoPorId(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "PedidoRESTful service: Exception reading pedido by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return pedido;
    }

    /**
     * RESTful GET method for reading all {@link Pedido} objects through an XML
     * representation.
     *
     * @return A List of Pedido objects containing data.
     */
    @GET
    @Produces({"application/xml"})
    public List<Pedido> findAll() {
        List<Pedido> pedidos = null;
        try {
            LOGGER.log(Level.INFO, "PedidoRESTful service: find all pedidos.");
            pedidos = ejb.buscarTodosLosPedidos();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "PedidoRESTful service: Exception reading all pedidos, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return pedidos;
    }
    
     /**
     * RESTful GET method for reading all {@link Pedido} objects that has a certain cliente
     * through an XML representation.
     * @param cliente The cliente value for the object.
     * @return A List of Pedido objects containing data.
     */
    @GET
    @Path("cliente/{id}")
    @Produces({"application/xml"})
    public List<Pedido> buscarPedidoDeCliente(@PathParam("id") Cliente cliente) {
        List<Pedido> pedidos=null;
        try {
            LOGGER.log(Level.INFO,"PedidoRESTful service: find pedido by cliente {0}.",cliente);
            pedidos= ejb.buscarPedidoDeCliente(cliente); 
            
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "PedidoRESTful service: Exception reading pedido by cliente, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return pedidos;
    }
    
   
    
}
