package com.vmall.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vmall.common.pojo.VmallResult;
import com.vmall.common.utils.HttpClientUtil;
import com.vmall.portal.pojo.SearchResult;
import com.vmall.portal.service.SearchService;


/*
 * 商品搜索Service
 */

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResult search(String queryString, int page) {
		//调用vmall-search服务
		//查询参数
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", String.valueOf(page));
		//调用服务
		try{
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			VmallResult vmallResult = VmallResult.formatToPojo(json, SearchResult.class);
			if(vmallResult.getStatus() == 200){
				SearchResult sr = (SearchResult)vmallResult.getData();
				return sr;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
