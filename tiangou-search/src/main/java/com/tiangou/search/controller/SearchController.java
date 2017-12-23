package com.tiangou.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.search.pojo.SearchResult;
import com.tiangou.search.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/q")
	@ResponseBody
	public TiangouResult search(@RequestParam(defaultValue="")String keyword, 
			@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="30")Integer rows) {
		
		try {
			//转换字符集
			keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
			SearchResult searchResult = searchService.search(keyword, page, rows);
			return TiangouResult.ok(searchResult);
		} catch (Exception e) {
			e.printStackTrace();
			return null;//TiangouResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		
	}

}