package com.vmall.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmall.common.pojo.VmallResult;
import com.vmall.rest.service.RedisService;

/**
 * 缓存同步Controller
 * @author liyafei
 *
 */

@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public VmallResult contentCacheSync(@PathVariable Long contentCid){
		VmallResult result = redisService.syncContent(contentCid);
		return result;
	}
}
