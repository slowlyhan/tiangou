package com.tiangou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiangou.common.pojo.EasyUIDataGridResult;
import com.tiangou.service.ItemParamItemService;
import com.tiangou.service.ItemParamService;



@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
		
	/*	@RequestMapping("/item/param/list")
		@ResponseBody
		public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
			EasyUIDataGridResult result = itemParamItemService.getItemParamList(page, rows);
			return result;
		}
		*/

	
	@RequestMapping("/showParam/{itemId}")
	public String showParam(@PathVariable Long itemId, Model model) {
		String string = itemParamItemService.getItemParemById(itemId);
		model.addAttribute("html", string);
		return "item-param";
	}
	
}
