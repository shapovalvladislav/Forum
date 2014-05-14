package model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the "Messages" database table.
 * 
 */
@Entity
@Table(name="\"Messages\"")
@NamedQueries( 
{
	@NamedQuery(name = "getMessages", query = "SELECT m FROM Message m"),
	@NamedQuery(name = "getMessagesByTopic", query = "SELECT m FROM Message m WHERE m.topicBean.id= ?1 ORDER BY m.date")
}
)
public class Message extends DomainSuperClass implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;

	private Timestamp date;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="\"profileId\"")
	private Profile profile;

	//bi-directional many-to-one association to Topic
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="topic")
	private Topic topicBean;

	public Message() {
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Topic getTopicBean() {
		return this.topicBean;
	}

	public void setTopicBean(Topic topicBean) {
		this.topicBean = topicBean;
	}

}