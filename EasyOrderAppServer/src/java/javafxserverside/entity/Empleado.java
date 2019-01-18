package javafxserverside.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity JPA class for employee data.
 *
 * @author Imanol
 */
@Entity
@Table(name = "empleado", schema = "easyorderappdb")
@NamedQueries({
	@NamedQuery(name="findAllEmployees",
		query="SELECT e FROM Empleado e ORDER BY e.login DESC"),
	@NamedQuery(name="findEmployeeByLogin",
		query="SELECT e FROM Empleado e WHERE e.login = :login ORDER BY e.login DESC"),
})
@XmlRootElement
public class Empleado implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id field for employee entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * Login value for the employee.
	 */
	//@Column(unique=true)
	private String login;
	/**
	 * Email value for the employee.
	 */
	//@Column(unique=true)
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
	 * Shifts of the employee.
	 */
	@ManyToMany(mappedBy = "empleados")
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
	 * Sets the login value of the employee.
	 *
	 * @param login The login value.
	 */
	public void setLogin(String login) {
		this.login = login;
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
	 * Sets the email value of the employee.
	 *
	 * @param email The email value.
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Sets the fullname value of the employee.
	 *
	 * @param fullname The fullname value.
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	 * Sets the status value of the employee.
	 *
	 * @param status The status value.
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
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
	 * Sets the privilege value of the employee.
	 *
	 * @param privilege The privilege value.
	 */
	public void setPrivilege(UserPrivilege privilege) {
		this.privilege = privilege;
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
	 * Sets the password value of the employee.
	 *
	 * @param password The password value.
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * Sets the last access value of the employee.
	 *
	 * @param lastAccess The last access value.
	 */
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
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
	 * Sets the last password change value of the employee.
	 *
	 * @param lastPasswordChange The last password change value.
	 */
	public void setLastPasswordChange(Date lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
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
	 * Sets the birth date value of the employee.
	 *
	 * @param fechaDeNacimiento The birth date value.
	 */
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
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
	 * Sets the phone value of the employee.
	 *
	 * @param telefono The telefono value.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	 * Sets the shift list of the employee.
	 *
	 * @param pedidos The shift list.
	 */
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	/**
	 * Gets the shift list of the employee.
	 *
	 * @return The shifts list.
	 */
	public List<Pedido> getPedidos() {
		return pedidos;
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
	 * This method compares two employee entities for equality. This
	 * implementation compares id field value for equality.
	 *
	 * @param object The object to compare to.
	 * @return True if objects are equal, otherwise false.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		final Empleado other = (Empleado) object;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * This method returns a String representation for an employee entity
	 * instance.
	 *
	 * @return The String representation for the employee object.
	 */
	@Override
	public String toString() {
		return "Empleado{" + "id=" + id + ", login=" + login + '}';
	}

}
