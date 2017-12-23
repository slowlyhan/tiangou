package com.tiangou.service;

import com.tiangou.common.pojo.EasyUIDataGridResult;
import com.tiangou.common.pojo.TiangouResult;

public interface ItemParamService {

	EasyUIDataGridResult getItemParamList(Integer page, Integer rows);

	TiangouResult checkParam(long cid);
	TiangouResult addItemParam(long cid, String template);
	TiangouResult getItemParemByCid(long cid);
	
	TiangouResult insertItemParam(Long cid, String paramData) ;
}
