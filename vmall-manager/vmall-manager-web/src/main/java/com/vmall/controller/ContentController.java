package com.vmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmall.common.pojo.VmallResult;
import com.vmall.pojo.TbContent;
import com.vmall.service.ContentService;



/**
 * 内容管理Controller
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public VmallResult insertContent(TbContent content) {
		VmallResult result = contentService.insertContent(content);
		return result;
	}
}
