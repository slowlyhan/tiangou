package com.tiangou.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.pojo.TbContent;
import com.tiangou.rest.service.ContentService;



/**
 * 内容管理服务 
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年8月19日上午11:40:55
 * @version 1.0
 */
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/{cid}")
	@ResponseBody
	public TiangouResult categoryList(@PathVariable Long cid) {
		List<TbContent> result =  contentService.getContentList(cid);
		return TiangouResult.ok(result);
	}
	
	@RequestMapping("/sync/content/{cid}")
	@ResponseBody
	public TiangouResult sysncContent(@PathVariable Long cid) {
		try {
			TiangouResult result = contentService.syncContent(cid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			//return TiangouResult.build(500, ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
}
