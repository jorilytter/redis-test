package fi.jori.test.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import redis.clients.jedis.Jedis;


public class MillionInserts {

	private static final int COUNT = 100_000;
	private static Jedis jedis;
	
	@Before
	public void setup() {
		jedis = new Jedis("localhost");
	}
	
	@After
	public void cleanUp() {
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < COUNT; i++) {
			jedis.del("million:"+i);
		}
		long time = System.currentTimeMillis()-start;
		System.out.println("Delete time ms: "+time);
	}
	
	@Ignore
	@Test
	public void insert() {
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < COUNT; i++) {
			jedis.set("million:"+i, "value of "+i);
		}
		long time = System.currentTimeMillis()-start;
		System.out.println("Insert time ms: "+time);
	}
	
	@Ignore
	@Test
	public void insertAndRead() {
		
		for(int i = 0; i < COUNT; i++) {
			jedis.set("million:"+i, "value of "+i);
		}
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < COUNT; i++) {
			jedis.get("million:"+i);
		}
		long time = System.currentTimeMillis()-start;
		System.out.println("Read time ms: "+time);
	}
}
