package com.vmall.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vmall.portal.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/exit")
	public String exit(HttpServletRequest request, HttpServletResponse response){
		userService.userExit(request, response);
		return "redirect:/index.html";
	}
}
