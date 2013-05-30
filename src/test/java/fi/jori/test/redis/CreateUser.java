package fi.jori.test.redis;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fi.jori.test.redis.dao.UserDao;
import fi.jori.test.redis.entity.User;
import fi.jori.test.redis.entity.UserInfo;

public class CreateUser {

	@Test
	public void createNewUser() throws Exception {
		
		UserDao dao = new UserDao();
		User user = dao.createUser(getUser());
		
		assertEquals(user.getInfo().getFirstName(), getUserInfo().getFirstName());
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
