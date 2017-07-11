package com.vmall.rest.service;

import com.vmall.common.pojo.VmallResult;

public interface RedisService {
	public VmallResult syncContent(long contentId);
}
