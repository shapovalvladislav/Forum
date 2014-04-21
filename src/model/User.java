package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the "Users" database table.
 * 
 */
@Entity
@Table(name="\"Users\"", schema = "public")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String login;

	private String password;

	//bi-directional many-to-one association to Role
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="role")
	private Role roleBean;

	//uni-directional one-to-one association to Profile
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="profile")
	private Profile profileBean;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRoleBean() {
		return this.roleBean;
	}

	public void setRoleBean(Role roleBean) {
		this.roleBean = roleBean;
	}

	public Profile getProfileBean() {
		return this.profileBean;
	}

	public void setProfileBean(Profile profileBean) {
		this.profileBean = profileBean;
	}

}