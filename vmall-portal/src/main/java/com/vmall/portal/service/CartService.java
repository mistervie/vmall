package com.vmall.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.vmall.common.pojo.VmallResult;
import com.vmall.portal.pojo.CartItem;

public interface CartService {
	VmallResult addItem(long id, int num, HttpServletRequest request, HttpServletResponse response);
	List<CartItem> getItemList(HttpServletRequest request, HttpServletResponse response);
	VmallResult deleteCartItem(long id,HttpServletRequest request, HttpServletResponse response);
}
