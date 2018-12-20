/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



/**
 * Entity JPA class for product data. The properties of this class are id, 
 * nombre, precioUnidad and stock.
 * @author Igor
 */
@Entity
public class Producto implements Serializable {
    /**
     * List of {@link Producto} belonging to the department.
     */
    @ManyToMany(mappedBy = "pedido")
    private List<Pedido> pedidos;
    private static final long serialVersionUID = 1L;
    /**
     * Id field for producto entity. 
     */
    @Id
    private Integer id;

    /**
     * Full name of the producto.
     */
    private String nombre;
    
    /**
     * Price by unit of the producto.
     */
    private int precioUnidad;
    
    /** 
     * Product stock.
     */
    private double stock;
    
    /**
     * Gets the id of the producto.
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * Sets the id of the producto.
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Gets the name of the producto.
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the producto.
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the precioUnidad of the product.
     * @return the precioUnidad
     */
    public int getPrecioUnidad() {
        return precioUnidad;
    }

    /**
     * Sets the precioUnidad of the product.
     * @param precioUnidad the precioUnidad to set
     */
    public void setPrecioUnidad(int precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    /**
     * Gets the stock of the product.
     * @return the stock
     */
    public double getStock() {
        return stock;
    }

    /**
     * Sets the precioUnidad of the product.
     * @param stock the stock to set
     */
    public void setStock(double stock) {
        this.stock = stock;
    }

     /**
     * Gets the list of pedidos .
     * @return A List of {@link Pedido}.
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * Sets the list of pedidos belonging to this department.
     * @param pedidos A List of {@link Pedido}.
     */
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    /**
     * HashCode method implementation for the entity.
     * @return An integer value as hashcode for the object. 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    /**
     * This method compares two product entities for equality. This implementation
     * compare id_product field value for equality.
     * @param object The object to compare to.
     * @return True if objects are equals, otherwise false.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * This method returns a String representation for a user entity instance.
     * @return The String representation for the user object. 
     */
    @Override
    public String toString() {
        return "javafxserverside.entity.Producto[ id=" + id + " ]";
    }

   
    
}
