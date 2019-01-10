/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Imanol
 */
@Entity
@Table(name = "producto", schema = "easyorderappdb")
@XmlRootElement
public class Producto implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private Integer stock;
	private Double precioUnidad;
	@ManyToMany(mappedBy="productos")
	private List<Pedido> pedidos;

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getStock() {
		return stock;
	}

	public Double getPrecioUnidad() {
		return precioUnidad;
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

	@Override
	public String toString() {
		return "javafxserverside.entity.Producto[ id=" + id + " ]";
	}
	
}
