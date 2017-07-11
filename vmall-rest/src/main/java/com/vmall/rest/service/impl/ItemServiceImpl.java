package com.vmall.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vmall.common.pojo.VmallResult;
import com.vmall.common.utils.JsonUtils;
import com.vmall.mapper.TbItemDescMapper;
import com.vmall.mapper.TbItemMapper;
import com.vmall.mapper.TbItemParamItemMapper;
import com.vmall.pojo.TbItem;
import com.vmall.pojo.TbItemDesc;
import com.vmall.pojo.TbItemParamItem;
import com.vmall.pojo.TbItemParamItemExample;
import com.vmall.pojo.TbItemParamItemExample.Criteria;
import com.vmall.rest.dao.JedisClient;
import com.vmall.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper paramItemMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	@Override
	public VmallResult getItemBaseInfo(long itemId) {
		
		//添加缓存逻辑
		try {
			//从缓存中读取数据
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			if(!StringUtils.isBlank(json)){
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return VmallResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//如果缓存没有，则从数据库中取数据
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		try {
			//写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
			//设置过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return VmallResult.ok(item);
	}

	@Override
	public VmallResult getItemDesc(long itemId) {
		//添加缓存逻辑
		try {
			//从缓存中读取数据
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			if(!StringUtils.isBlank(json)){
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return VmallResult.ok(itemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//如果缓存没有，则从数据库中取数据
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		
		try {
			//写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
			//设置过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return VmallResult.ok(itemDesc);		
	}

	@Override
	public VmallResult getItemParam(long itemId) {
		//添加缓存逻辑
				try {
					//从缓存中读取数据
					String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
					if(!StringUtils.isBlank(json)){
						TbItemParamItem tbItemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
						return VmallResult.ok(tbItemParamItem);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//如果缓存没有，则从数据库中取数据
				TbItemParamItemExample example = new TbItemParamItemExample();
				Criteria criteria = example.createCriteria();
				criteria.andItemIdEqualTo(itemId);
				List<TbItemParamItem> list = paramItemMapper.selectByExampleWithBLOBs(example);
				if(list != null && list.size() > 0){
					TbItemParamItem tbItemParamItem = list.get(0);
					try {
						//写入缓存
						jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(tbItemParamItem));
						//设置过期时间
						jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return VmallResult.ok(tbItemParamItem); 
				}
					return VmallResult.build(400, "无此商品规格");
				
	}
	
}
