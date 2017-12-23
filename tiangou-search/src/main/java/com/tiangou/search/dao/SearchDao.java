package com.tiangou.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.tiangou.search.pojo.SearchResult;

public interface SearchDao {
	public SearchResult search(SolrQuery query);
}
