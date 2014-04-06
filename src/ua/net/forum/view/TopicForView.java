package ua.net.forum.view;

import java.sql.Timestamp;

public class TopicForView {
	private int id;
	private String name;
	private int msgCount;
	private Timestamp lastMsgDate;
	private String lastMsgUserName;
	private int lastMsgUserId;
	

	
	public TopicForView(int id, String name, int msgCount,
			Timestamp lastMsgDate, String lastMsgUserName, int lastMsgUserId) {
		super();
		this.id = id;
		this.name = name;
		this.msgCount = msgCount;
		this.lastMsgDate = lastMsgDate;
		this.lastMsgUserName = lastMsgUserName;
		this.lastMsgUserId = lastMsgUserId;
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
	public String getLastMsgUserName() {
		return lastMsgUserName;
	}
	public void setLastMsgUserName(String lastMsgUserName) {
		this.lastMsgUserName = lastMsgUserName;
	}
	public int getLastMsgUserId() {
		return lastMsgUserId;
	}
	public void setLastMsgUserId(int lastMsgUserId) {
		this.lastMsgUserId = lastMsgUserId;
	}
	
	
	
}
