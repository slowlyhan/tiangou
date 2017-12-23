package com.tiangou.service;

import org.springframework.stereotype.Service;

import com.tiangou.common.pojo.EasyUIDataGridResult;
import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.pojo.TbItem;
import com.tiangou.pojo.TbItemDesc;

@Service
public interface ItemService {
	TbItem getItemById(long id);
	EasyUIDataGridResult getItemList(int page, int rows);
	TiangouResult addItem(TbItem item, TbItemDesc itemDesc, String itemParams);
}
