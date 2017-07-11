package com.vmall.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestJedis {
	
	@Test
	public void testJedis(){
		Jedis jedis = new Jedis("192.168.56.101", 6379);
		jedis.auth("123456");
		jedis.set("key1", "jedis test");
		String str = jedis.get("key1");
		System.out.println(str);
		jedis.close();
	}
	
	@Test
	public void testJedisPool(){
		JedisPool pool = new JedisPool("192.168.56.101", 6379);
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		String str = jedis.get("key1");
		System.out.println(str);
		jedis.close();
	}
	
	@Test
	public void testJedisCluster(){
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.56.101", 7001));
		nodes.add(new HostAndPort("192.168.56.101", 7002));
		nodes.add(new HostAndPort("192.168.56.101", 7003));
		nodes.add(new HostAndPort("192.168.56.101", 7004));
		nodes.add(new HostAndPort("192.168.56.101", 7005));
		nodes.add(new HostAndPort("192.168.56.101", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("key1", "1000");
		String str = cluster.get("key1");
		System.out.println(str );
		cluster.close();
	}
	
	@Test
	public void testSpringJedisPool(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool)ac.getBean("redisClient");
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		String str = jedis.get("key1");
		System.out.println(str);
		jedis.close();
	}
	
	@Test
	public void testSpringJedisCluster(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster cluster = (JedisCluster)ac.getBean("redisClient");
		cluster.set("key1", "1000");
		String str = cluster.get("key1");
		System.out.println(str );
		cluster.close();
	}
	
}
