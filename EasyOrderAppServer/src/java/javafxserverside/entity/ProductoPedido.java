/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @ManyToOne
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
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPedido() != null && getProducto() != null ? getPedido().hashCode()+ getProducto().hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "javafxserverside.entity.ProductoPedido[ pedido=" + getPedido().getId() + " producto=" + getProducto().getId()+"]";
    }

    
}
