package fi.jori.test.redis;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;

import fi.jori.test.redis.dao.UserDao;
import fi.jori.test.redis.entity.User;
import fi.jori.test.redis.entity.UserInfo;



public class CreateUser {

	@Test
	public void createNewUser() throws Exception {
		
		UserDao dao = new UserDao();
		Map<String, String> info = dao.createUser(getUser());
		
		assertEquals(getUser().getEmail(), info.get("email"));
		assertEquals(getExpectedInfo(), info.get("info"));
	}
	
	private Object getExpectedInfo() {

		Gson gson = new Gson();
		return gson.toJson(getUserInfo());
	}

	private User getUser() {

		User user = new User();
		user.setEmail("jori@foo.bar");
		user.setInfo(getUserInfo());
		
		return user;
	}

	private UserInfo getUserInfo() {

		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Jori");
		userInfo.setLastName("Lytter");
		
		return userInfo;
	}

}
