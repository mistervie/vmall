package com.vmall.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vmall.common.pojo.VmallResult;
import com.vmall.common.utils.ExceptionUtil;
import com.vmall.rest.dao.JedisClient;
import com.vmall.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService{
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public VmallResult syncContent(long contentId) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentId));
		} catch (Exception e) {
			e.printStackTrace();
			return VmallResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return VmallResult.ok();
	}
	
}
