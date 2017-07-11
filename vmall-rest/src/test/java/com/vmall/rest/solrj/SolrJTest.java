package com.vmall.rest.solrj;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {
	
	@Test
	public void addDocument() throws Exception{
		//SolrClient client = new HttpSolrClient("http://192.168.56.101:8888/solr/mycore");
		SolrClient client = new HttpSolrClient.Builder("http://192.168.56.101:8888/solr/mycore").build();
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品1");
		document.addField("item_price", 12345);
		
		client.add(document);
		client.commit();
	}
}
