package com.tiangou.rest.service;

import com.tiangou.pojo.TbItem;
import com.tiangou.pojo.TbItemDesc;
import com.tiangou.pojo.TbItemParamItem;

public interface ItemService {
	public TbItem getItemById(Long itemId);
	
	public TbItemDesc getItemDescById(Long itemId);
	
	public TbItemParamItem getItemParamById(Long itemId);
}
