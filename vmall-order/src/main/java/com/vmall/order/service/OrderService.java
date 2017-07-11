package com.vmall.order.service;

import java.util.List;

import com.vmall.common.pojo.VmallResult;
import com.vmall.pojo.TbOrder;
import com.vmall.pojo.TbOrderItem;
import com.vmall.pojo.TbOrderShipping;

public interface OrderService {
	VmallResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
