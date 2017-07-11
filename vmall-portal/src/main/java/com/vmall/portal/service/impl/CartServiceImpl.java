package com.vmall.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vmall.common.pojo.VmallResult;
import com.vmall.common.utils.CookieUtils;
import com.vmall.common.utils.HttpClientUtil;
import com.vmall.common.utils.JsonUtils;
import com.vmall.pojo.TbItem;
import com.vmall.portal.pojo.CartItem;
import com.vmall.portal.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITME_INFO_URL}")
	private String ITME_INFO_URL;

	@Override
	public VmallResult addItem(long id, int num, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> list = getCartItemList(request);
		CartItem cItem = null;
		for (CartItem cartItem : list) {
			if(id == cartItem.getId()){
				cartItem.setNum(cartItem.getNum() + num);
				cItem = cartItem;
				break;
			}
		}
		if(cItem == null){
			cItem = new CartItem();
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITME_INFO_URL+id);
			VmallResult result = VmallResult.formatToPojo(json, TbItem.class);
			if(result.getStatus() == 200){
				TbItem item = (TbItem)result.getData();
				cItem.setId(item.getId());
				cItem.setTitle(item.getTitle());
				cItem.setPrice(item.getPrice());
				cItem.setNum(num);
				cItem.setImage(item.getImage()==null?"":item.getImage().split(",")[0]);
			}
			list.add(cItem);
		}
		CookieUtils.setCookie(request, response, "VM_CART", JsonUtils.objectToJson(list), true);
		return VmallResult.ok();
	}

	
	
	private List<CartItem> getCartItemList(HttpServletRequest request){
		String json = CookieUtils.getCookieValue(request, "VM_CART", true);
		if(json == null){
			return new ArrayList<>();
		}
		try{
			List<CartItem> list = JsonUtils.jsonToList(json, CartItem.class);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}



	@Override
	public List<CartItem> getItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> list = getCartItemList(request);
		return list;
	}



	@Override
	public VmallResult deleteCartItem(long id, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> list = getCartItemList(request);
		for (CartItem cartItem : list) {
			if(cartItem.getId() == id){
				list.remove(cartItem);
				break;
			}
		}
		
		CookieUtils.setCookie(request, response, "VM_CART", JsonUtils.objectToJson(list), true);
		
		return VmallResult.ok();
	}
	
	
	
}
