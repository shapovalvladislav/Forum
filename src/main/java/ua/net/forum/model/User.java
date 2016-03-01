package ua.net.forum.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="\"Users\"")
@NamedQueries(
{
    @NamedQuery(name = "getUsers", query = "SELECT u FROM User u"),
    @NamedQuery(name = "getUserByLogin", query = "SELECT u FROM User u WHERE u.login=?1"),
    @NamedQuery(name = "getUserByLoginAndPassword", query = "SELECT u FROM User u WHERE u.login=?1 AND u.password=?2")
}
)
public class User extends DomainSuperClass implements Serializable {

    private static final long serialVersionUID = 1L;
    private String login;
    private String password;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="role")
    private Role roleBean;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="profile")
    private Profile profileBean;

    public User() {
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