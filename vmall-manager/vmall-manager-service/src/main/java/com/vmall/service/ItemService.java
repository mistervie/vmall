package com.vmall.service;

import com.vmall.common.pojo.EUDataGridResult;
import com.vmall.common.pojo.VmallResult;
import com.vmall.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long itemId);
	EUDataGridResult getItemList(int page, int rows);
	VmallResult createItem(TbItem item, String desc, String itemParam) throws Exception;
}
