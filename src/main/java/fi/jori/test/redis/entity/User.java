package fi.jori.test.redis.entity;


public class User {

	private String email;
	private UserInfo info;
	
	public String getEmail() {
	
		return email;
	}
	
	public void setEmail(String email) {
	
		this.email = email;
	}
	
	public UserInfo getInfo() {
	
		return info;
	}
	
	public void setInfo(UserInfo info) {
	
		this.info = info;
	}
	
}
