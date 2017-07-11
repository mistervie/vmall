package com.vmall.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
	
import com.vmall.common.utils.JsonUtils;
import com.vmall.rest.pojo.CatResult;
import com.vmall.rest.service.ItemCatService;

@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping(value = "/itemcat/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback){
		CatResult result = itemCatService.getItemCatList();
		String json = JsonUtils.objectToJson(result);
		String res = callback + "(" + json + ");";
		return res;
	}
	
	@RequestMapping("/itemcat/list1")
	@ResponseBody
	public Object getItemCatList1(String callback){
		CatResult result = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
}
