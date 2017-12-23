package com.tiangou.rest.service;

import java.util.List;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.pojo.TbContent;

public interface ContentService {

	//TiangouResult getContentList(long categoryId);
	List<TbContent> getContentList(Long cid);
	TiangouResult syncContent(Long cid);
}
