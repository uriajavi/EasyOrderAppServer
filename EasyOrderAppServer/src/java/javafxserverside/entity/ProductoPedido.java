/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.MERGE;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad clase JPA para datos relacionados entre pedido y productos.
 * @author Leticia Garcia
 */


@Entity
@Table(name = "productopedido", schema = "easyorderappdb")
@XmlRootElement
public class ProductoPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    private Pedido pedido;
    @Id
    @ManyToOne(fetch=FetchType.EAGER)   
    private Producto producto;
    private Integer cantidad;
    
    
    
    //Getters and setters
    
    
    /**
     * @return the pedido
     */
   @XmlTransient
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    //METODOAK
     /**
     * HashCode method implementation for the entity.
     *
     * @return An integer value as hashcode for the object.
     */

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPedido() != null && getProducto() != null ? getPedido().hashCode()+ getProducto().hashCode() : 0);
        return hash;
    }

     /**
     * This method compares two productopedido entities for equality. This
     * implementation compare login field value for equality.
     *
     * @param object The object to compare to.
     * @return True if objects are equals, otherwise false.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoPedido)) {
            return false;
        }
        ProductoPedido other = (ProductoPedido) object;
        if ((this.getPedido()== null && other.getPedido() != null) || (this.getPedido() != null && !this.pedido.equals(other.pedido))
            || (this.getProducto()== null && other.getProducto() != null) || (this.getProducto() != null && !this.producto.equals(other.producto))) {
            return false;
        }
        return true;
    }
    
      /**
     * This method returns a String representation for a productopedido entity instance.
     *
     * @return The String representation for the productopedido object.
     */
    //Para que imprima el error 
    @Override
    public String toString() {
        String retorno = "javafxserverside.entity.ProductoPedido[ pedido=";
        if(getPedido() != null) retorno= retorno + getPedido().getId(); 
        if(getProducto() != null) retorno = retorno + " producto=" + getProducto().getId();
        
        return retorno;
    }

    
}
