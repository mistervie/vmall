package com.vmall.service;

import java.util.List;

import com.vmall.common.pojo.EUTreeNode;
import com.vmall.common.pojo.VmallResult;

public interface ContentCategoryService {
	List<EUTreeNode> getCategoryList(long parentId);
	VmallResult insertContentCategory(long parentId, String name);
}
