package com.vmall.rest.service;

import java.util.List;

import com.vmall.pojo.TbContent;

public interface ContentService {

	List<TbContent> getContentList(long contentCid);
}
