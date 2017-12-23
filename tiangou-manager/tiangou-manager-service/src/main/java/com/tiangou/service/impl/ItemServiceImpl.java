package com.tiangou.service.impl;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiangou.common.pojo.EasyUIDataGridResult;
import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.common.utils.IDUtils;
import com.tiangou.mapper.TbItemDescMapper;
import com.tiangou.mapper.TbItemMapper;
import com.tiangou.mapper.TbItemParamItemMapper;
import com.tiangou.pojo.TbItem;
import com.tiangou.pojo.TbItemDesc;
import com.tiangou.pojo.TbItemExample;
import com.tiangou.pojo.TbItemExample.Criteria;
import com.tiangou.pojo.TbItemParamItem;
import com.tiangou.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemById(long id) {
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
				TbItemExample example = new TbItemExample();
				//创建查询条件
				Criteria criteria = example.createCriteria();
				criteria.andIdEqualTo(id);
				List<TbItem> list = itemMapper.selectByExample(example);
				//判断list中是否为空
				TbItem item = null;
				if (list != null && list.size() > 0) {
					item = list.get(0);
				}
				return item;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult(pageInfo.getTotal(),list);
		/*result.setTotal(pageInfo.getTotal());
		result.setRows(list);*/
		
		return result;
	}

	@Override
	public TiangouResult addItem(TbItem item, TbItemDesc itemDesc, String itemParams) {
			//生成商品id
			//可以使用redis的自增长key，在没有redis之前使用时间+随机数策略生成
			Long itemId = IDUtils.genItemId();
			//补全不完整的字段
			item.setId(itemId);
			item.setStatus((byte) 1);
			Date date = new Date();
			item.setCreated(date);
			item.setUpdated(date);
			//把数据插入到商品表
			itemMapper.insert(item);
			//添加商品描述
			itemDesc.setItemId(itemId);
			itemDesc.setCreated(date);
			itemDesc.setUpdated(date);
			//把数据插入到商品描述表
			itemDescMapper.insert(itemDesc);
			
			//把商品的规格参数插入到tb_item_param_item中
			TbItemParamItem itemParamItem = new TbItemParamItem();
			itemParamItem.setItemId(itemId);
			itemParamItem.setParamData(itemParams);
			itemParamItem.setCreated(date);
			itemParamItem.setUpdated(date);
			itemParamItemMapper.insert(itemParamItem);
			
		
		return TiangouResult.ok();
	}

}