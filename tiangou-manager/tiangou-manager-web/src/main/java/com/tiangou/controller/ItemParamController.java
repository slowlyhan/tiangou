package com.tiangou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiangou.common.pojo.EasyUIDataGridResult;
import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.service.ItemParamService;


/**
 * 规格参数模板Controller
 * <p>Title: ItemParamController</p>
 * <p>Description: </p>
 * @author	hanyu
 * @version 1.0
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TiangouResult checkItemParam(@PathVariable Long cid) {
		TiangouResult result = itemParamService.checkParam(cid);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TiangouResult addItemParam(@PathVariable Long cid, String paramData) {
		TiangouResult result = itemParamService.addItemParam(cid, paramData);
		return result;
	}
	
	@RequestMapping("/cid/{cid}")
	//@RequestMapping("/{cid}")
	@ResponseBody
	public TiangouResult getItemParamByCid(@PathVariable Long cid) {
		TiangouResult result = itemParamService.getItemParemByCid(cid);
		return result;
	}
	
}
