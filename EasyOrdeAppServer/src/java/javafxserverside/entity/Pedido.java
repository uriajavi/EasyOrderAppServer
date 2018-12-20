/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Entidad clase JPA para datos de pedido. Las propiedades de esta clase son ,
 * Nombre, fecha tramitado, fecha preparado, fecha entregado y estado.
 *
 * @author Leticia Garcia
 */
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Campo de identificación para la entidad de pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * Name of the pedido.
     */
    private String nombre;
    /**
     * Fecha tramitado del pedido
     */
    private Timestamp fechaTramitado;
    /**
     * Fecha preparado del pedido
     */
    private Timestamp fechaPreparado;
    /**
     * Fecha entregado del pedido
     */
    private Timestamp fechaEntregado;
    
    /**
     * {@link EstadoPedido} value for the pedido.
     */
    @Enumerated(EnumType.ORDINAL)
    private EstadoPedido estado;
    
    @ManyToMany
    @JoinTable(name="PEDIDO_EMPLEADO")
    private List<Empleado>empleados;

    @ManyToMany
    @JoinTable(name="PEDIDO_PRODUCTO")
    private List<Producto>productos;
    
    @ManyToOne
    private Cliente cliente; 
    
    //Getters and setters
  
    
    
     /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fechaTramitado
     */
    public Timestamp getFechaTramitado() {
        return fechaTramitado;
    }

    /**
     * @param fechaTramitado the fechaTramitado to set
     */
    public void setFechaTramitado(Timestamp fechaTramitado) {
        this.fechaTramitado = fechaTramitado;
    }

    /**
     * @return the fechaPreparado
     */
    public Timestamp getFechaPreparado() {
        return fechaPreparado;
    }

    /**
     * @param fechaPreparado the fechaPreparado to set
     */
    public void setFechaPreparado(Timestamp fechaPreparado) {
        this.fechaPreparado = fechaPreparado;
    }

    /**
     * @return the fechaEntregado
     */
    public Timestamp getFechaEntregado() {
        return fechaEntregado;
    }

    /**
     * @param fechaEntregado the fechaEntregado to set
     */
    public void setFechaEntregado(Timestamp fechaEntregado) {
        this.fechaEntregado = fechaEntregado;
    }

    /**
     * @return the estado
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    /**
     * @return the empleados
     */
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    /**
     * @return the productos
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    
    

    //Metodoak
    
     /**
     * HashCode method implementation for the entity.
     * @return An integer value as hashcode for the object. 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    /**
     * This method compares two user entities for equality. This implementation
     * compare id field value for equality.
     * @param object The object to compare to.
     * @return True if objects are equals, otherwise false.
     */
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * This method returns a String representation for a pedido entity instance.
     * @return The String representation for the pedido object. 
     */
    @Override
    public String toString() {
        return "javafxserverside.entity.Pedido[ id=" + getId() + " ]";
    }

}