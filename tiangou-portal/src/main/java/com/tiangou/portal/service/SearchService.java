package com.tiangou.portal.service;

import org.springframework.stereotype.Service;

import com.tiangou.portal.pojo.SearchResult;


@Service
public interface SearchService {
	public SearchResult search(String queryString, int page, int rows) ;

}
