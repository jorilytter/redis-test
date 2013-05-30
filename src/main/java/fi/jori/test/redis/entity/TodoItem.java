package fi.jori.test.redis.entity;

import java.util.Date;


public class TodoItem {

	private String uid;
	private String email;
	private String text;
	private Date created;
	
	public String getUid() {
	
		return uid;
	}
	
	public void setUid(String uid) {
	
		this.uid = uid;
	}
	
	public String getEmail() {
	
		return email;
	}
	
	public void setEmail(String email) {
	
		this.email = email;
	}
	
	public String getText() {
	
		return text;
	}
	
	public void setText(String text) {
	
		this.text = text;
	}

	public Date getCreated() {

		return created;
	}

	public void setCreated(Date created) {

		this.created = created;
	}

	@Override
	public String toString() {

		return "TodoItem [uid=" + uid + ", email=" + email + ", text=" + text + ", created=" + created + "]";
	}
	

}
