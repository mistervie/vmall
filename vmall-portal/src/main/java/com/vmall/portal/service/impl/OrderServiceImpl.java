package com.vmall.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vmall.common.pojo.VmallResult;
import com.vmall.common.utils.HttpClientUtil;
import com.vmall.common.utils.JsonUtils;
import com.vmall.portal.pojo.Order;
import com.vmall.portal.service.OrderService;

/**
 * 订单处理Service
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	

	@Override
	public VmallResult createOrder(Order order) {
		//调用创建订单服务之前补全用户信息。
		//从cookie中后取TT_TOKEN的内容，根据token调用sso系统的服务根据token换取用户信息。
		
		//调用vmall-order的服务提交订单。
		String orderStr = JsonUtils.objectToJson(order);
		String url = ORDER_BASE_URL + ORDER_CREATE_URL+"--------"+orderStr;
		System.out.println(url);
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, orderStr);
		//把json转换成vmallResult
		
		VmallResult vmallResult = VmallResult.format(json);
		/*
		if (vmallResult.getStatus() == 200) {
			Object orderId = vmallResult.getData();
			return orderId.toString();
		}
		return "";
		*/
		return vmallResult;
	}

}