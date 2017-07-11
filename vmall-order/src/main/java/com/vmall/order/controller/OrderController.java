package com.vmall.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmall.common.pojo.VmallResult;
import com.vmall.common.utils.ExceptionUtil;
import com.vmall.order.pojo.Order;
import com.vmall.order.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/order/create",method=RequestMethod.POST)
	@ResponseBody
	public VmallResult createOrder(@RequestBody Order order){
		VmallResult result = null;
		
		try {
			 result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
		} catch (Exception e) {
			e.printStackTrace();
			return VmallResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}
	
}
