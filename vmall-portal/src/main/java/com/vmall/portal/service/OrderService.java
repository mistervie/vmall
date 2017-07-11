package com.vmall.portal.service;

import com.vmall.common.pojo.VmallResult;
import com.vmall.portal.pojo.Order;

public interface OrderService {

	VmallResult createOrder(Order order);
}
