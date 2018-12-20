package javafxserverside.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity JPA class for account data.
 *
 * @author Imanol
 */
@Entity
@Table(name = "cuenta", schema = "easyorderappdb")
public class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Id field for account entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * Account number of the account.
	 */
	private String numeroCuenta;
	/**
	 * Security code of the accout.
	 */
	private String codigoSeguridad;
	/**
	 * Owner of the account.
	 */
	private String propietario;
	/**
	 * Balance of the account.
	 */
	private Double saldo;
	/**
	 * Client owning the account.
	 */
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private Cliente cliente;

	/**
	 * Gets id value of the account.
	 *
	 * @return The id value.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets id value of the account.
	 *
	 * @param id The id value.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the account number of the account.
	 *
	 * @return The account number.
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the account number of the account.
	 *
	 * @param numeroCuenta The account number value.
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the security code of the account.
	 *
	 * @return The security code value.
	 */
	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}

	/**
	 * Sets the security code of the account.
	 *
	 * @param codigoSeguridad The security code value.
	 */
	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	/**
	 * Gets the owner of the account.
	 *
	 * @return The owner value.
	 */
	public String getPropietario() {
		return propietario;
	}

	/**
	 * Sets the owner of the account.
	 *
	 * @param propietario The owner value.
	 */
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	/**
	 * Gets the balance of the account.
	 *
	 * @return The balance value.
	 */
	public Double getSaldo() {
		return saldo;
	}

	/**
	 * Sets the balance of the account.
	 *
	 * @param saldo The balance value.
	 */
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

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
	 * This method compares two account entities for equality. This
	 * implementation compares id field value for equality.
	 *
	 * @param obj The object to compare to.
	 * @return True if object are equal, otherwise false.
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
		final Cuenta other = (Cuenta) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * This method returns a String representation for an account entity
	 * instance.
	 *
	 * @return The String representation for the account object.
	 */
	@Override
	public String toString() {
		return "Cuenta{" + "id=" + id + ", numeroCuenta=" + numeroCuenta + '}';
	}

}
