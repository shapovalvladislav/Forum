package ua.net.forum.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="\"Sections\"")
@NamedQueries(
{
	@NamedQuery(name = "getSections", query = "SELECT s FROM Section s")
}
)
public class Section extends DomainSuperClass implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	private String name;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="moderator")
	private Profile profile;

	@OneToMany(mappedBy="sectionBean", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Topic> topics;

	public Section() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Topic addTopic(Topic topic) {
		getTopics().add(topic);
		topic.setSectionBean(this);

		return topic;
	}

	public Topic removeTopic(Topic topic) {
		getTopics().remove(topic);
		topic.setSectionBean(null);

		return topic;
	}

}