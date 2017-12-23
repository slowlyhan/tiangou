package com.tiangou.portal.service;

import com.tiangou.portal.pojo.Item;

public interface ItemService {

	Item getItemById(long itemId);
	String getItemDesc(long itemId);
	String getItemParam(long itemId);
}
