package com.vmall.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vmall.common.pojo.VmallResult;
import com.vmall.common.utils.HttpClientUtil;
import com.vmall.common.utils.JsonUtils;
import com.vmall.pojo.TbContent;
import com.vmall.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	@Value("${REST_BASE_URL}")
	private String rest_base_url;
	
	@Value("${REST_INDEX_AD_URL}")
	private String rest_index_ad_url;
	
	@Override
	public String getContentList() {
		String result = HttpClientUtil.doGet(rest_base_url + rest_index_ad_url);
		try {
			VmallResult vmallResult = VmallResult.formatToList(result, TbContent.class);
			List<TbContent> list = (List<TbContent>)vmallResult.getData();
			List<Map> resultList = new ArrayList<>();
			for (TbContent tbContent : list) {
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("heightB", 240);
				map.put("widthB", 550);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
