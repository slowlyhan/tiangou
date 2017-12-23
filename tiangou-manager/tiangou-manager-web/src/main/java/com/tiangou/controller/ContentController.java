package com.tiangou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.common.utils.HttpClientUtil;
import com.tiangou.pojo.TbContent;
import com.tiangou.service.ContentService;



/**
 * 内容管理
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年8月19日上午11:24:41
 * @version 1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@Value("REST_BASE_URL")
	private String REST_BASE_URL;
	@Value("REST_CONITENT_SYNC_URL")
	private String REST_CONITENT_SYNC_URL;
	@RequestMapping("/save")
	@ResponseBody
	private TiangouResult saveContent(TbContent content) {
		TiangouResult result = contentService.insertContent(content);
		
		HttpClientUtil.doGet(REST_BASE_URL+REST_CONITENT_SYNC_URL+content.getId());
		return result;
	}
}
