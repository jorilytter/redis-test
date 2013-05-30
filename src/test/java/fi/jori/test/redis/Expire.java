package fi.jori.test.redis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import redis.clients.jedis.Jedis;


public class Expire {

	@Test
	public void expireTwoSeconds() throws InterruptedException {
		
		Jedis jedis = new Jedis("localhost");
		jedis.set("expire:2", "Still here");
		
		assertNotNull(jedis.get("expire:2"));
		
		jedis.expire("expire:2", 2);
		assertNotNull("nil".equals(jedis.get("expire:2")));
		
		Thread.sleep(2200);
		assertNull(jedis.get("expire:2"));
	}
}
