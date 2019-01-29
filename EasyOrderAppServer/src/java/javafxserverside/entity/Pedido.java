/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad clase JPA para datos de pedido.
 *
 * @author Leticia Garcia
 */
@Entity
@Table(name = "pedido", schema = "easyorderappdb")
@NamedQueries({
    @NamedQuery(name = "buscarTodosLosPedidos", query = "SELECT p FROM Pedido p ORDER BY p.nombre DESC"
    )
    ,
     @NamedQuery(name = "buscarPedidoDeCliente", query = "SELECT p FROM Pedido p WHERE p.cliente =:cliente"
    )
})
@XmlRootElement
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id field for pedido entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * Nombre del pedido
     */
    private String nombre;
    /**
     * fechaTramitado del pedido
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private java.util.Date fechaTramitado;
    /**
     * fechaPreparado del pedido
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private java.util.Date fechaPreparado;
    /**
     * fechaEntregado del pedido
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private java.util.Date fechaEntregado;
    /**
     * {@link Cliente} del pedido
     */
    @ManyToOne
    private Cliente cliente;
    /**
     * {@link EstadoPedido} value for the pedido.
     */
    @Enumerated(EnumType.ORDINAL)
    private EstadoPedido estadoPedido;

    /**
     * {@link Empleado} del pedido
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PEDIDO_EMPLEADO", schema = "easyorderappdb")
    private List<Empleado> empleados;
    /**
     * {@link ProductoPedido} del pedido
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = MERGE)
    private List<ProductoPedido> productos;

    /**
     * fechaPedido del pedido
     */
    //Cambios de ultimo momento
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private java.util.Date fechaPedido;
    /**
     * horaPedido del pedido
     */
    private String horaPedido;

    //Getters and setters
    /**
     * Gets cliente value for pedido
     *
     * @return the cliente value
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets cliente value for pedido.
     *
     * @param cliente The cliente value.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Gets nombre value for pedido
     *
     * @return the nombre value
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre value for pedido.
     *
     * @param nombre The nombre value.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Gets fechaTramitado value for pedido
     *
     * @return the fechaTramitado value
     */
    public java.util.Date getFechaTramitado() {
        return fechaTramitado;
    }

    /**
     * Sets fechaTramitado value for pedido.
     *
     * @param fechaTramitado The fechaTramitado value.
     */
    public void setFechaTramitado(java.util.Date fechaTramitado) {
        this.fechaTramitado = fechaTramitado;
    }

    /**
     * Gets fechaPreparado value for pedido
     *
     * @return the fechaPreparado value
     */
    public java.util.Date getFechaPreparado() {
        return fechaPreparado;
    }

    /**
     * Sets fechaPreparado value for pedido.
     *
     * @param fechaPreparado value
     */
    public void setFechaPreparado(java.util.Date fechaPreparado) {
        this.fechaPreparado = fechaPreparado;
    }

    /**
     * Gets fechaEntregado value for pedido
     *
     * @return the fechaEntregado value
     */
    public java.util.Date getFechaEntregado() {
        return fechaEntregado;
    }

    /**
     * Sets fechaEntregado value for pedido.
     *
     * @param fechaEntregado value
     */
    public void setFechaEntregado(java.util.Date fechaEntregado) {
        this.fechaEntregado = fechaEntregado;
    }

    /**
     * Gets estadoPedido value for pedido
     *
     * @return the estadoPedido value
     */
    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    /**
     * Sets estadoPedido value for pedido.
     *
     * @param estadoPedido valie
     */
    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    /**
     * Gets id value for pedido
     *
     * @return the id value
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id value for pedido.
     *
     * @param id value
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets empleados value for pedido
     *
     * @return the empleados value
     */
    @XmlTransient
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     * Sets empleados value for pedido.
     *
     * @param empleados The empleados value.
     */
    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    /**
     * Gets productos value for pedido
     *
     * @return the productos value
     */
    public List<ProductoPedido> getProductos() {
        return productos;
    }

    /**
     * Sets productos value for pedido.
     *
     * @param productos The productos value.
     */
    public void setProductos(List<ProductoPedido> productos) {
        this.productos = productos;
    }

    /**
     * Gets fechaPedido value for pedido
     *
     * @return the fechaPedido value
     */
    public Date getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Sets fechaPedido value for pedido.
     *
     * @param fechaPedido The fechaPedido value.
     */
    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * Gets horaPedido value for pedido
     *
     * @return the horaPedido value
     */
    public String getHora() {
        return horaPedido;
    }

    /**
     * Sets horaPedido value for pedido.
     *
     * @param horaPedido The horaPedido value.
     */
    public void setHora(String horaPedido) {
        this.horaPedido = horaPedido;
    }

    //Metodoak   
    /**
     * HashCode method implementation for the entity.
     *
     * @return An integer value as hashcode for the object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * This method compares two pedido entities for equality. This
     * implementation compare login field value for equality.
     *
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * This method returns a String representation for a pedido entity instance.
     *
     * @return The String representation for the pedido object.
     */
    @Override
    public String toString() {
        return "javafxserverside.entity.Pedido[ id=" + id + " ]";
    }

}
