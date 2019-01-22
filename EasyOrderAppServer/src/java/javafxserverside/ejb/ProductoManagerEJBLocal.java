/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.ejb;

import java.util.List;
import javafxserverside.entity.Producto;
import javafxserverside.exception.CreateException;
import javafxserverside.exception.DeleteException;
import javafxserverside.exception.ReadException;
import javafxserverside.exception.UpdateException;
import javax.ejb.Local;

/**
 * EJB Local Interface for managing Product entity CRUD operations. 
 * @author Igor
 */
@Local
public interface ProductoManagerEJBLocal {
     /**
     * Finds a {@link Producto} by its id. 
     * @param id The login for the product to be found.
     * @return The {@link Producto} object containing product data. 
     * @throws ReadException If there is any Exception during processing.
     */
    public Producto findProductById(Integer id) throws ReadException;
     
    /**
     * Finds a List of {@link Producto} objects containing data for all products in the
     * application data storage.
     * @return A List of {@link Producto} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Producto> findAllProducts() throws ReadException;
    
    /**
     * Creates a Product and stores it in the underlying application storage. 
     * @param producto The {@link Producto} object containing the user data. 
     * @throws CreateException If there is any Exception during processing.
     */
    public void createProduct(Producto producto) throws CreateException;
    
    /**
     * Updates a product's data in the underlying application storage. 
     * @param producto The {@link Producto} object containing the product data. 
     * @throws UpdateException If there is any Exception during processing.
     */
    public void updateProduct(Producto producto) throws UpdateException;
    /**
     * Deletes a product's data in the underlying application storage. 
     * @param producto The {@link Producto} object containing the product data. 
     * @throws DeleteException If there is any Exception during processing.
     */
    public void deleteProduct(Producto producto) throws DeleteException;
}
