package fi.jori.test.redis.dao;

import com.google.gson.Gson;

import fi.jori.test.redis.entity.User;
import fi.jori.test.redis.entity.UserInfo;

public class UserDao extends GenericDao {

	private static final String USER_PREFIX = "user:email:";
	private static final Gson GSON = new Gson();
	
	public User createUser(User user) throws Exception {
		
		String userKey = USER_PREFIX+user.getEmail();
		String userValue = GSON.toJson(user.getInfo());

		insert(userKey, userValue);
		
		return user;
	}

	public User fetchUser(String email) {

		String userKey = USER_PREFIX+email;
		String userData = fetch(userKey);

		UserInfo info = GSON.fromJson(userData, UserInfo.class);
		
		User user = new User();
		user.setEmail(email);
		user.setInfo(info);
		
		return user;
	}
}
