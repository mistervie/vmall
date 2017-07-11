package com.vmall.service;

import java.util.List;

import com.vmall.common.pojo.EUTreeNode;

public interface ItemCatService {

	List<EUTreeNode> getCatList(long parentId);
}
