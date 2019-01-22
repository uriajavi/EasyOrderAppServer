/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxserverside.entity.Producto;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB class for managing Producto entity CRUD operations.
 * @author Igor
 */
@Stateless
public class ProductoManagerEJB  implements ProductoManagerEJBLocal{
     /**
     * Logger for the class.
     */
    private static final Logger LOGGER =
            Logger.getLogger("javafxserverside");
    /**
     * Entity manager object.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Finds a List of {@link Producto} objects containing data for all products with certain
     * profile value.
     * @param id The profile value for the products to be found.
     * @return A List of {@link Producto} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Producto findProductById(Integer id) throws ReadException {
        Producto producto=null;
        try{
            LOGGER.info("ProductoManager: Finding product by id.");
            producto=em.find(Producto.class, id);
            if(producto!=null){
                LOGGER.log(Level.INFO,"ProductoManager: Product found {0}",producto.getId());
            }
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductoManager: Exception Finding product by id:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return producto;
    }
    
    /**
     * Finds a List of {@link Producto} objects containing data for all products in the
     * application data storage.
     * @return A List of {@link Producto} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Producto> findAllProducts() throws ReadException {
        List<Producto> productos=null;
        try{
            LOGGER.info("ProductoManager: Reading all products.");
            productos=em.createNamedQuery("findAllProducts").getResultList();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductoManager: Exception reading all products:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return productos;
    }
    
    /**
     * Creates a Product and stores it in the underlying application storage. 
     * @param producto The {@link Producto} object containing the product data. 
     * @throws CreateException If there is any Exception during processing.
     */
    @Override
    public void createProduct(Producto producto) throws CreateException {
        LOGGER.info("ProductoManager: Creating product.");
        try{
            em.persist(producto);
            LOGGER.info("ProductoManager: Product created.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductoManager: Exception creating product.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }
    
    /**
     * Updates a product's data in the underlying application storage. 
     * @param producto The {@link Producto} object containing the product data. 
     * @throws UpdateException If there is any Exception during processing.
     */
    @Override
    public void updateProduct(Producto producto) throws UpdateException {
        LOGGER.info("ProductoManager: Updating producto.");
        try{
            //if(!em.contains(producto))em.merge(producto);
            em.merge(producto);
            em.flush();
            LOGGER.info("ProductoManager: Producto updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductoManager: Exception updating product.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }
    
    /**
     * Deletes a product's data in the underlying application storage. 
     * @param producto The {@link Producto} object containing the product data. 
     * @throws DeleteException If there is any Exception during processing.
     */
    @Override
    public void deleteProduct(Producto producto) throws DeleteException {
        LOGGER.info("ProductoManager: Deleting producto.");
        try{
            producto=em.merge(producto);
            em.remove(producto);
            LOGGER.info("ProductoManager: Producto deleted.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductoManager: Exception deleting producto.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }
    
}
