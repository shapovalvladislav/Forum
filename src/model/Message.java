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
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String content;

	private Timestamp date;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="\"profileId\"")
	private Profile profile;

	//bi-directional many-to-one association to Topic
	@ManyToOne
	@JoinColumn(name="topic")
	private Topic topicBean;

	public Message() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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