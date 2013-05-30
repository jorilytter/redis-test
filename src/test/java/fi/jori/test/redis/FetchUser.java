package fi.jori.test.redis;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import fi.jori.test.redis.dao.UserDao;
import fi.jori.test.redis.entity.User;
import fi.jori.test.redis.entity.UserInfo;

import redis.clients.jedis.Jedis;


public class FetchUser {

	@Before
	public void setUp() {
		
		Jedis jedis = new Jedis("localhost");
		jedis.hset("user:email:jori@foo.bar", "email", "jori@foo.bar");
		jedis.hset("user:email:jori@foo.bar", "info", getUserInfo());
	}

	@Test
	public void getUser() {
		
		UserDao dao = new UserDao();
		User user = dao.fetchUser("jori@foo.bar");
		
		assertEquals("jori@foo.bar", user.getEmail());
		assertEquals("Jori", user.getInfo().getFirstName());
		assertEquals("Lytter", user.getInfo().getLastName());
	}
	
	private String getUserInfo() {

		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Jori");
		userInfo.setLastName("Lytter");

		Gson gson = new Gson();
		return gson.toJson(userInfo);
	}
}
