package fi.jori.test.redis.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public abstract class GenericDao {
	
	private static final String SUFFIX = ":*";
	private Jedis jedis;

	public GenericDao() {
		jedis = new Jedis("localhost");
	}
	
	protected Map<String, String> insert(String userKey, String userValue) {

		jedis.set(userKey, userValue);
		String value = jedis.get(userKey);
		Map<String, String> result = new HashMap<>();
		result.put(userKey, value);
		
		return result;
	}
	
	protected Map<String, String> insert(String key, String field, String value) {
		
		jedis.hset(key, field, value);
		return jedis.hgetAll(key);
	}
	
	protected Map<String, String> fetchMap(String key) {
		
		return jedis.hgetAll(key);
	}
	
	protected String fetch(String key) {
		
		return jedis.get(key);
	}
	
	protected Set<String> findKeys(String prefix) {
		
		return jedis.keys(prefix+SUFFIX);
	}
}
