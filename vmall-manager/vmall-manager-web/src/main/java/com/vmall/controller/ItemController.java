package com.vmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmall.common.pojo.EUDataGridResult;
import com.vmall.common.pojo.VmallResult;
import com.vmall.pojo.TbItem;
import com.vmall.service.ItemService;

/**
 * 商品管理Controller
 */

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	private VmallResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		VmallResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}
}
