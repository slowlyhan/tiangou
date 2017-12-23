package com.tiangou.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.common.utils.JsonUtils;
import com.tiangou.mapper.TbContentMapper;
import com.tiangou.pojo.TbContent;
import com.tiangou.pojo.TbContentExample;
import com.tiangou.pojo.TbContentExample.Criteria;
import com.tiangou.rest.component.JedisClient;
import com.tiangou.rest.service.ContentService;



/**
 * 内容管理服务
 * <p>
 * Title: ContentServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 入云龙
 * @date 2015年8月19日上午11:35:12
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("REDIS_CONTENT_KEY")
	private String REDIS_CONTENT_KEY;
	
	@Override
	public List<TbContent> getContentList(Long cid) {
		
		try {
			//从redis中取缓存数据
			String json = jedisClient.hget(REDIS_CONTENT_KEY, cid+"");
			if (!StringUtils.isBlank(json)) {
				//把json转换成List
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据cid查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		
		
		//返回结果之前，向缓存中添加数据
				try {
					//为了规范key可以使用hash
					//定义一个保存内容的key，hash中每个项就是cid
					//value是list，需要把list转换成json数据。
					jedisClient.hset(REDIS_CONTENT_KEY, cid+"", JsonUtils.objectToJson(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
		return list;
	}

	@Override
	public TiangouResult syncContent(Long cid) {
		jedisClient.hdel(REDIS_CONTENT_KEY, cid + "");
		return TiangouResult.ok();
	}


}
