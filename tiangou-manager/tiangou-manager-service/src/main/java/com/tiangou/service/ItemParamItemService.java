package com.tiangou.service;

import com.tiangou.common.pojo.EasyUIDataGridResult;

public interface ItemParamItemService {

	String getItemParemById(long itemId);
	
	EasyUIDataGridResult getItemParamList(Integer page, Integer rows);

}
