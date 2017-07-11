package com.vmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmall.common.pojo.VmallResult;
import com.vmall.pojo.TbItemParam;
import com.vmall.service.ItemParamService;

/**
 * 商品规格参数模板管理Controller
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public VmallResult getItemParamByCid(@PathVariable Long itemCatId) {
		VmallResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public VmallResult insertItemParam(@PathVariable Long cid, String paramData) {
		//创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		VmallResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
}
