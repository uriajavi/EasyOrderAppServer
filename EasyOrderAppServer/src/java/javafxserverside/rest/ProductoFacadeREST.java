/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxserverside.ejb.ProductoManagerEJBLocal;
import javafxserverside.entity.Producto;
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
 * RESTful web service class exposing CRUD operations for {@link Producto} entities.
 * @author Igor
 */
@Path("productos")
public class ProductoFacadeREST {

    /**
     * Logger for class methods.
     */
    private static final Logger LOGGER =
            Logger.getLogger("javafxserverside");
    
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private ProductoManagerEJBLocal ejb;

    /**
     * RESTful POST method for creating {@link Producto} objects from XML representation.
     * @param producto The object containing product data.
     */
    @POST
    @Consumes({"application/xml"})
    public void create(Producto producto) {
        try {
            LOGGER.log(Level.INFO,"ProductoRESTful service: create {0}.",producto);
            ejb.createProduct(producto);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, 
                    "ProductoRESTful service: Exception creating product, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * RESTful PUT method for updating {@link Producto} objects from XML representation.
     * @param producto The object containing user data.
     */
    @PUT
    @Consumes({"application/xml"})
    public void update(Producto producto) {
        try {
            LOGGER.log(Level.INFO,"ProductoRESTful service: update {0}.",producto);
            ejb.updateProduct(producto);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "ProductoRESTful service: Exception updating user, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * RESTful DELETE method for deleting {@link Producto} objects from id.
     * @param id The id for the object to be deleted.
     */
    @DELETE
    @Path("{id}")
    //@Consumes({"application/xml", "application/json"})
    public void delete(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO,"ProductRESTful service: delete Producto by id={0}.",id);
            ejb.deleteProduct(ejb.findProductById(id));
        } catch (ReadException | DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "ProductoRESTful service: Exception deleting producto by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        } 
    }

    /**
     * RESTful GET method for reading {@link User} objects through an XML representation.
     * @param id The id for the object to be read.
     * @return The User object containing data. 
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml"})
    public Producto find(@PathParam("id") Integer id) {
        Producto producto=null;
        try {
            LOGGER.log(Level.INFO,"ProductoRESTful service: find Producto by id={0}.",id);
            producto=ejb.findProductById(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "ProductoRESTful service: Exception reading producto by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return producto;
    }

    /**
     * RESTful GET method for reading all {@link Producto} objects through an XML representation.
     * @return A List of User objects containing data.
     */
    @GET
    @Produces({"application/xml"})
    public List<Producto> findAll() {
        List<Producto> productos=null;
        try {
            LOGGER.log(Level.INFO,"ProductoRESTful service: find all products.");
            productos=ejb.findAllProducts();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "ProductoRESTful service: Exception reading all products, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return productos;
    }

   
    
}
