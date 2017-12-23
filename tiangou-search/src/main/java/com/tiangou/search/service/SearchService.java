package com.tiangou.search.service;

import org.springframework.stereotype.Service;

import com.tiangou.search.pojo.SearchResult;

@Service
public interface SearchService {
	public SearchResult search(String queryString, int page, int rows) ;

}
