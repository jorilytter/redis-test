package fi.jori.test.redis.dao;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;


public abstract class GenericDao {
	
	private static final String RESULT_OK = "OK";
	private Jedis jedis;

	public GenericDao() {
		jedis = new Jedis("localhost");
	}
	
	protected String insert(String key, String value) throws Exception {
		
		String result = jedis.set(key, value);

		if(!RESULT_OK.equals(result)) {
			throw new Exception(getInsertExceptionMessage(key, value));
		}
		
		return jedis.get(key);
	}
	
	protected Map<String, String> insert(String key, String field, String value) {
		
		jedis.hset(key, field, value);
		return jedis.hgetAll(key);
	}

	private String getInsertExceptionMessage(String key, String value) {

		return "Exception occured inserting "+key+" - "+value;
	}
	
	protected Map<String, String> fetch(String key) {
		
		return jedis.hgetAll(key);
	}
	
	protected Set<String> findKeys(String prefix) {
		
		return jedis.keys(prefix+":*");
	}
}
