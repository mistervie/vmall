package com.vmall.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vmall.common.pojo.VmallResult;
import com.vmall.pojo.TbUser;

public interface UserService {
	TbUser getUserByToken(String token);
	VmallResult userExit(HttpServletRequest request, HttpServletResponse response);
}
