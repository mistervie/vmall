package com.vmall.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmall.common.pojo.VmallResult;
import com.vmall.rest.service.ItemService;

/*
 * 商品信息Controller
 */

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public VmallResult getItemInfo(@PathVariable Long itemId){
		VmallResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public VmallResult getItemDesc(@PathVariable Long itemId){
		VmallResult result = itemService.getItemDesc(itemId);
		return result;
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public VmallResult getItemParam(@PathVariable Long itemId){
		VmallResult result = itemService.getItemParam(itemId);
		return result;
	}
	
}
