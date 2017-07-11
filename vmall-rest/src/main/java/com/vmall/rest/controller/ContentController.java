package com.vmall.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmall.common.pojo.VmallResult;
import com.vmall.common.utils.ExceptionUtil;
import com.vmall.rest.service.ContentService;
import com.vmall.pojo.TbContent;
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public VmallResult getContentList(@PathVariable Long contentCategoryId){
		try {
			List<TbContent> list = contentService.getContentList(contentCategoryId);
			return VmallResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return VmallResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
