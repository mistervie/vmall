package com.vmall.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vmall.common.utils.JsonUtils;
import com.vmall.mapper.TbContentMapper;
import com.vmall.pojo.TbContent;
import com.vmall.pojo.TbContentExample;
import com.vmall.pojo.TbContentExample.Criteria;
import com.vmall.rest.dao.JedisClient;
import com.vmall.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentList(long contentCid) {
		//从缓存中取数据
		try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentCid));
			if(!StringUtils.isBlank(result)){
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//把数据存入缓存
		try {
			String cacheString = JsonUtils.objectToJson(list);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentCid), cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
