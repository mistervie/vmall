package com.vmall.portal.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vmall.common.pojo.VmallResult;
import com.vmall.common.utils.CookieUtils;
import com.vmall.common.utils.HttpClientUtil;
import com.vmall.pojo.TbUser;
import com.vmall.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	@Override
	public TbUser getUserByToken(String token) {
		try {
			String json = HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+token);
			VmallResult result = VmallResult.formatToPojo(json, TbUser.class);
			if(result.getStatus() == 200){
				TbUser user = (TbUser)result.getData();
				return user;
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	@Override
	public VmallResult userExit(HttpServletRequest request, HttpServletResponse response) {
		String token = CookieUtils.getCookieValue(request, "VM_TOKEN");
		CookieUtils.deleteCookie(request, response, "VM_TOKEN");
		String json = HttpClientUtil.doPost(SSO_BASE_URL+"/user/exit/"+token);
		return VmallResult.formatToPojo(json, VmallResult.class);
	}

}
