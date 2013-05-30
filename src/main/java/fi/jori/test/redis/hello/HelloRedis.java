package fi.jori.test.redis.hello;

import redis.clients.jedis.Jedis;


public class HelloRedis {

	public static void main(String[] args) {

		Jedis jedis = new Jedis("localhost");
		jedis.set("hello:redis", "hello from the world of redis");
		
		String hello = jedis.get("hello:redis");
		
		System.out.println(hello);
	}

}
