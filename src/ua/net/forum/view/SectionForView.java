package ua.net.forum.view;

import java.sql.Timestamp;

public class SectionForView {
	private int id;
	private String name;
	private int topicCount;
	private int msgCount;
	private Timestamp lastMsgDate;
	private String userName;
	private int userId;
	private int moderatorId;
	private String moderatorNickName;
	
	public SectionForView(int id, String name, int topicCount, int msgCount,
			Timestamp date, String userName, int userId,int moderatorId,String moderatorNickName) {
		this.id = id;
		this.name = name;
		this.topicCount = topicCount;
		this.msgCount = msgCount;
		this.lastMsgDate = date;
		this.userName = userName;
		this.userId = userId;
		this.setModeratorId(moderatorId);
		this.setModeratorNickName(moderatorNickName);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	public int getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}

	public Timestamp getLastMsgDate() {
		return lastMsgDate;
	}

	public void setLastMsgDate(Timestamp lastMsgDate) {
		this.lastMsgDate = lastMsgDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getModeratorNickName() {
		return moderatorNickName;
	}

	public void setModeratorNickName(String moderatorNickName) {
		this.moderatorNickName = moderatorNickName;
	}

	public int getModeratorId() {
		return moderatorId;
	}

	public void setModeratorId(int moderatorId) {
		this.moderatorId = moderatorId;
	}
}
