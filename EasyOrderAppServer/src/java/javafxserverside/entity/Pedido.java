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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Entidad clase JPA para datos de pedido. Las propiedades de esta clase son ,
 * Nombre, fecha tramitado, fecha preparado, fecha entregado y estado.
 *
 * @author Leticia Garcia
 */
@Entity
@Table(name = "pedido", schema = "easyorderappdb")
@XmlRootElement
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nombre;
	private Timestamp fechaTramitado;
	private Timestamp fechaPreparado;
	private Timestamp fechaEntregado;
	@ManyToOne
	private Cliente cliente;
	@Enumerated(EnumType.ORDINAL)
	private EstadoPedido estadoPedido;
	@ManyToMany
	@JoinTable(name="PEDIDO_EMPLEADO", schema="easyorderappdb" )
	private List<Empleado> empleados;
	@ManyToMany
	@JoinTable(name="PEDIDO_PRODUCTO", schema="easyorderappdb")
	private List<Producto> productos;
        
        
        
        
        
        
        //Getters and setters

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaTramitado(Timestamp fechaTramitado) {
		this.fechaTramitado = fechaTramitado;
	}

	public void setFechaPreparado(Timestamp fechaPreparado) {
		this.fechaPreparado = fechaPreparado;
	}

	public void setFechaEntregado(Timestamp fechaEntregado) {
		this.fechaEntregado = fechaEntregado;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombre() {
		return nombre;
	}

	public Timestamp getFechaTramitado() {
		return fechaTramitado;
	}

	public Timestamp getFechaPreparado() {
		return fechaPreparado;
	}

	public Timestamp getFechaEntregado() {
		return fechaEntregado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

        
        
        //Metodoak
        
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

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

	@Override
	public String toString() {
		return "javafxserverside.entity.Pedido[ id=" + id + " ]";
	}
	
}