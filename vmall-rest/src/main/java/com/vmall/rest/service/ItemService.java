package com.vmall.rest.service;

import com.vmall.common.pojo.VmallResult;

public interface ItemService {

	VmallResult getItemBaseInfo(long itemId);
	VmallResult getItemDesc(long itemId);
	VmallResult getItemParam(long itemId);
}
