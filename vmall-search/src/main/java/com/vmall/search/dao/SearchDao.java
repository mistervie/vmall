package com.vmall.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.vmall.search.pojo.SearchResult;

public interface SearchDao {

	SearchResult search(SolrQuery query) throws Exception;
}
