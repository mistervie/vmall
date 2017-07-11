package com.vmall.service;

import com.vmall.common.pojo.VmallResult;
import com.vmall.pojo.TbItemParam;

public interface ItemParamService {

	VmallResult getItemParamByCid(long cid);
	VmallResult insertItemParam(TbItemParam itemParam);
}
