package ua.net.forum.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="\"Roles\"")
@NamedQueries(
{
	@NamedQuery(name = "getRoles", query = "SELECT r FROM Role r"),
	@NamedQuery(name = "getRoleByName", query = "SELECT r FROM Role r WHERE r.name=?1")
}
)
public class Role extends DomainSuperClass implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	@OneToMany(mappedBy="roleBean")
	private List<User> users;

	public Role() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRoleBean(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRoleBean(null);

		return user;
	}

}