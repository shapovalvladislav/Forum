package ua.net.forum.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="\"Profiles\"")
@NamedQueries(
{
	@NamedQuery(name = "getProfiles", query = "SELECT p FROM Profile p"),
	@NamedQuery(name = "getProfileById", query = "SELECT p FROM Profile p WHERE p.id=:id"),
	@NamedQuery(name = "getTopProfiles", query = "SELECT p FROM Profile p ORDER BY p.msgCount DESC")
}
)
public class Profile extends DomainSuperClass implements Serializable {

    private static final long serialVersionUID = 1L;
	private String about;

	@Temporal(TemporalType.DATE)
	@Column(name="\"birthDate\"")
	private Date birthDate;

	private String email;

	@Column(name="\"fullName\"")
	private String fullName;

	private byte[] icon;

	@Column(name="\"msgCount\"")
	private Integer msgCount;

	@Column(name="\"nickName\"")
	private String nickName;

	private String sex;

	@Column(name="\"topicCount\"")
	private Integer topicCount;

	@OneToMany(mappedBy="profile")
	private List<Message> messages;

	@OneToMany(mappedBy="profile")
	private List<Section> sections;

	@OneToMany(mappedBy="profile")
	private List<Topic> topics;

	public Profile() {
	}

	public String getAbout() {
		return this.about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public byte[] getIcon() {
		return this.icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public Integer getMsgCount() {
		return this.msgCount;
	}

	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getTopicCount() {
		return this.topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setProfile(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setProfile(null);

		return message;
	}

	public List<Section> getSections() {
		return this.sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Section addSection(Section section) {
		getSections().add(section);
		section.setProfile(this);

		return section;
	}

	public Section removeSection(Section section) {
		getSections().remove(section);
		section.setProfile(null);

		return section;
	}

	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Topic addTopic(Topic topic) {
		getTopics().add(topic);
		topic.setProfile(this);

		return topic;
	}

	public Topic removeTopic(Topic topic) {
		getTopics().remove(topic);
		topic.setProfile(null);

		return topic;
	}

}