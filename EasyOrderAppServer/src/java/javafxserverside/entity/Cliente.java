package javafxserverside.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity JPA class for the client data.
 *
 * @author Imanol
 */
@Entity
@Table(name = "cliente", schema = "easyorderappdb")
@NamedQueries({
	@NamedQuery(name = "findAllClients",
		query = "SELECT c FROM Cliente c ORDER BY c.login DESC")
})
@XmlRootElement
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Id field for client entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * Login value for the employee.
	 */
	private String login;
	/**
	 * Email value for the employee.
	 */
	private String email;
	/**
	 * Full name of the employee.
	 */
	private String fullname;
	/**
	 * {@link UserStatus} value for the employee.
	 */
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;
	/**
	 * {@link UserPrivilege} value for the employee.
	 */
	@Enumerated(EnumType.ORDINAL)
	private UserPrivilege privilege;
	/**
	 * Password of the employee.
	 */
	private String password;
	/**
	 * Last time the employee signed in.
	 */
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date lastAccess;
	/**
	 * Last time the employee changed password.
	 */
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date lastPasswordChange;
	/**
	 * Birth date of the employee.
	 */
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fechaDeNacimiento;
	/**
	 * Phone number of the employee.
	 */
	private String telefono;
	/**
	 * Residence of the client.
	 */
	@ManyToOne
	private Domicilio domicilio;
	/**
	 * Account of the client.
	 */
	@OneToOne
	private Cuenta cuenta;
	/**
	 * Shifts of the client.
	 */
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	/**
	 * Gets the id of the employee.
	 *
	 * @return The id value.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id value of the employee.
	 *
	 * @param id The id value.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets login value of the employee.
	 *
	 * @return The login value.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login value of the employee.
	 *
	 * @param login The login value.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets email value of the employee.
	 *
	 * @return The email value.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email value of the employee.
	 *
	 * @param email The email value.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets fullname value of the employee.
	 *
	 * @return The fullname value.
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * Sets the fullname value of the employee.
	 *
	 * @param fullname The fullname value.
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * Gets the status value of the employee.
	 *
	 * @return The status value.
	 */
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status value of the employee.
	 *
	 * @param status The status value.
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	/**
	 * Gets the privilege value of the employee.
	 *
	 * @return The privilege value.
	 */
	public UserPrivilege getPrivilege() {
		return privilege;
	}

	/**
	 * Sets the privilege value of the employee.
	 *
	 * @param privilege The privilege value.
	 */
	public void setPrivilege(UserPrivilege privilege) {
		this.privilege = privilege;
	}

	/**
	 * Gets the password value of the employee.
	 *
	 * @return The password value.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password value of the employee.
	 *
	 * @param password The password value.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the last access value of the employee.
	 *
	 * @return The last access value.
	 */
	public Date getLastAccess() {
		return lastAccess;
	}

	/**
	 * Sets the last access value of the employee.
	 *
	 * @param lastAccess The last access value.
	 */
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	/**
	 * Gets the last password change value of the employee.
	 *
	 * @return The last password change.
	 */
	public Date getLastPasswordChange() {
		return lastPasswordChange;
	}

	/**
	 * Sets the last password change value of the employee.
	 *
	 * @param lastPasswordChange The last password change value.
	 */
	public void setLastPasswordChange(Date lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	/**
	 * Gets the birth date value of the employee.
	 *
	 * @return The birth date value.
	 */
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	/**
	 * Sets the birth date value of the employee.
	 *
	 * @param fechaDeNacimiento The birth date value.
	 */
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Gets the phone value of the employee.
	 *
	 * @return The phone value.
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Sets the phone value of the employee.
	 *
	 * @param telefono The telefono value.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Gets the residence value of the client.
	 *
	 * @return The residence value.
	 */
	public Domicilio getDomicilio() {
		return domicilio;
	}

	/**
	 * Sets the residence value of the client.
	 *
	 * @param domicilio The residence value.
	 */
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * Gets the account value of the client.
	 *
	 * @return The account value.
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the account value of the client.
	 *
	 * @param cuenta The account value.
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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
	 * This method compares two client entities for equality. This
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
		final Cliente other = (Cliente) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * This method returns a String representation for a client entity intance.
	 *
	 * @return The String representation for the client object.
	 */
	@Override
	public String toString() {
		return "Cliente{" + "id=" + id + ", login=" + login + '}';
	}

}
