package com.tiangou.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.search.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/importall")
	@ResponseBody
	public TiangouResult importAll() {
		try {
			TiangouResult result = itemService.importItems();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			//return TiangouResult.build(500, ExceptionUtil.getStackTrace(e));
			return null;
		}
	}

}