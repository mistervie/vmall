package com.vmall.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vmall.common.pojo.VmallResult;
import com.vmall.pojo.TbUser;

public interface UserService {
	VmallResult checkUser(String str, Integer type);
	VmallResult createUser(TbUser user);
	VmallResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	VmallResult getUserByToken(String token);
	VmallResult userExit(String token, HttpServletRequest request, HttpServletResponse response);
}
