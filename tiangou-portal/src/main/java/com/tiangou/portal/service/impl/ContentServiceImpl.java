package com.tiangou.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.common.utils.HttpClientUtil;
import com.tiangou.common.utils.JsonUtils;
import com.tiangou.pojo.TbContent;
import com.tiangou.portal.pojo.AdNode;
import com.tiangou.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_URL}")
	private String REST_CONTENT_URL;
	@Value("${REST_CONTENT_AD1_CID}")
	private String REST_CONTENT_AD1_CID;

	/**
	 * 获得大广告位的内容
	 * <p>Title: getAd1List</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.taotao.portal.service.ContentService#getAd1List()
	 */
	@Override
	public String getAd1List() {
		//调用服务获得数据
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_URL + REST_CONTENT_AD1_CID);
		//把json转换成java对象
		TiangouResult tiangouResult = TiangouResult.formatToList(json, TbContent.class);
		//取data属性，内容列表
		List<TbContent> contentList = (List<TbContent>) tiangouResult.getData();
		//把内容列表转换成AdNode列表
		List<AdNode> resultList = new ArrayList<>();
		for (TbContent tbContent : contentList) {
			AdNode node = new AdNode();
			node.setHeight(240);
			node.setWidth(670);
			node.setSrc(tbContent.getPic());
			
			node.setHeightB(240);
			node.setWidthB(550);
			node.setSrcB(tbContent.getPic2());
			
			node.setAlt(tbContent.getSubTitle());
			node.setHref(tbContent.getUrl());
			
			resultList.add(node);
		}
		//需要把resultList转换成json数据,为什么转换回去
		String resultJson = JsonUtils.objectToJson(resultList);
		return resultJson;
	}

}