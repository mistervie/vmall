package com.vmall.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

	@RequestMapping("/register")
	public String pageReg(){
		return "register";
	}
	
	@RequestMapping("/login")
	public String pageLogin(String redirect, Model model){
		model.addAttribute("redirect", redirect);
		return "login";
	}
}
