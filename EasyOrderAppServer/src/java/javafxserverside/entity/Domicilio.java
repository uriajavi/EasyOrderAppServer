package javafxserverside.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity JPA class for residence.
 *
 * @author Imanol
 */
@Entity
@Table(name = "domicilio", schema = "easyorderappdb")
public class Domicilio implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Id field for residence entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * Town of the residence.
	 */
	private String localidad;
	/**
	 * CP of the residence.
	 */
	private String codigoPostal;
	/**
	 * Address of the residence.
	 */
	private String direccion;
	/**
	 * Clients with this residence.
	 */
	@OneToMany(mappedBy = "cliente")
	private List<Cliente> clientes;

	/**
	 * Gets the id value of the residence.
	 *
	 * @return The id value.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id value of the residence.
	 *
	 * @param id The id value.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the town value of the residence.
	 *
	 * @return The town value.
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Sets the town value of the residence.
	 *
	 * @param localidad The town value.
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Gets the CP value of the residence.
	 *
	 * @return The CP value.
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Sets the CP value of the residence.
	 *
	 * @param codigoPostal The CP value.
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Gets the address value of the residence.
	 *
	 * @return The address value.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Sets the address value of the residence.
	 *
	 * @param direccion The address value.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * HashCode method implementation for the entity.
	 *
	 * @return An integer value as hashcode for the object.
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (getId() != null ? getId().hashCode() : 0);
		return hash;
	}

	/**
	 * This method compares two residence entities for equality. This
	 * implementation compares id field value for equality.
	 *
	 * @param obj The object to compare to.
	 * @return True if objects are equal, otherwise false.
	 */
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
		final Domicilio other = (Domicilio) obj;
		if (!Objects.equals(this.localidad, other.localidad)) {
			return false;
		}
		if (!Objects.equals(this.codigoPostal, other.codigoPostal)) {
			return false;
		}
		if (!Objects.equals(this.direccion, other.direccion)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * This method returns a String representation for a residence entity
	 * instance.
	 *
	 * @return The String representation for the residence object.
	 */
	@Override
	public String toString() {
		return "Domicilio{" + "id=" + getId() + '}';
	}
}
