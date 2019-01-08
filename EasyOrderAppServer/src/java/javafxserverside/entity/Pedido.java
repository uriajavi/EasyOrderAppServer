/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Imanol
 */
@Entity
@Table(name = "pedido", schema = "easyorderappdb")
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
	@JoinTable(name="pedido_empleado",
		schema="easyorderappdb")
	private List<Empleado> empleados;
	@ManyToMany
	@JoinTable(name="pedido_producto",
		schema="easyorderappdb")
	private List<Producto> productos;

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Pedido other = (Pedido) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "javafxserverside.entity.Pedido[ id=" + id + " ]";
	}

}
