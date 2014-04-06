package ua.net.forum.view;

import java.sql.Timestamp;
import java.util.Collection;

import ua.net.forum.db.DBQuery;
import model.Section;

public class SectionForView {
	private int id;
	private String name;
	private int topicCount;
	private int msgCount;
	private Timestamp lastMsgDate;
	private String userName;
	private int userId;
	
	public SectionForView(int id, String name, int topicCount, int msgCount,
			Timestamp date, String userName, int userId) {
		this.id = id;
		this.name = name;
		this.topicCount = topicCount;
		this.msgCount = msgCount;
		this.lastMsgDate = date;
		this.userName = userName;
		this.userId = userId;
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

	
	
}
