package com.vmall.portal.service;

import com.vmall.portal.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String queryString, int page);
}
