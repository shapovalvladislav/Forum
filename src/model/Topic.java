package model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the "Topics" database table.
 * 
 */
@Entity
@Table(name="\"Topics\"")
@NamedQuery(name="Topic.findAll", query="SELECT t FROM Topic t")
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="\"lastChange\"")
	private Timestamp lastChange;

	@Column(name="\"msgCount\"")
	private Integer msgCount;

	private String name;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="topicBean", fetch=FetchType.EAGER)
	private List<Message> messages;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="\"profileId\"")
	private Profile profile;

	//bi-directional many-to-one association to Section
	@ManyToOne
	@JoinColumn(name="section")
	private Section sectionBean;

	public Topic() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getLastChange() {
		return this.lastChange;
	}

	public void setLastChange(Timestamp lastChange) {
		this.lastChange = lastChange;
	}

	public Integer getMsgCount() {
		return this.msgCount;
	}

	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setTopicBean(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setTopicBean(null);

		return message;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Section getSectionBean() {
		return this.sectionBean;
	}

	public void setSectionBean(Section sectionBean) {
		this.sectionBean = sectionBean;
	}

}