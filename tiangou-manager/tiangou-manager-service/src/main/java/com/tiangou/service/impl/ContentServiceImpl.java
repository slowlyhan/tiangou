package com.tiangou.service.impl;

import java.util.Date;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.mapper.TbContentMapper;
import com.tiangou.pojo.TbContent;
import com.tiangou.service.ContentService;


/**
 * 内容管理Service
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年8月19日上午11:20:03
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Override
	public TiangouResult insertContent(TbContent content) {
		try {
			//补全字段
			content.setUpdated(new Date());
			content.setCreated(new Date());
			//插入数据
			contentMapper.insert(content);
			
		} catch (Exception e) {
			e.printStackTrace();
			//return TiangouResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return TiangouResult.ok();
	}

}
