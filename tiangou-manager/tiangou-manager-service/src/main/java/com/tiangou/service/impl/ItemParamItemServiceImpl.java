package com.tiangou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiangou.common.pojo.EasyUIDataGridResult;
import com.tiangou.common.utils.JsonUtils;
import com.tiangou.mapper.TbItemParamItemMapper;
import com.tiangou.pojo.TbItemParam;
import com.tiangou.pojo.TbItemParamExample;
import com.tiangou.pojo.TbItemParamItem;
import com.tiangou.pojo.TbItemParamItemExample;
import com.tiangou.pojo.TbItemParamItemExample.Criteria;
import com.tiangou.service.ItemParamItemService;


/**
 * 取规格参数信息
 * <p>Title: ItemParamItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年8月16日下午2:55:12
 * @version 1.0
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
		//分页处理
				PageHelper.startPage(page, rows);
				//执行查询
				TbItemParamItemExample example = new TbItemParamItemExample();
				List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
				//取分页信息
				PageInfo<TbItemParamItem> pageInfo = new PageInfo<>(list);
				//返回处理结果
				EasyUIDataGridResult result = new EasyUIDataGridResult(pageInfo.getTotal(),list);
				
				return result;
	}
	
	@Override
	public String getItemParemById(long itemId) {
		
		//创建查询条件
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		//如果没有取到规格参数返回空串。
		if (null == list || list.isEmpty()) {
			return "";
		}
		//取到规格参数
		TbItemParamItem itemParamItem = list.get(0);
		String paramData = itemParamItem.getParamData();
		//把规格参数信息转换成java对象
		List<Map> paramList = JsonUtils.jsonToList(paramData, Map.class);
		//根据list生成html
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("     <tbody>\n");
		for(Map param:paramList) {
			sb.append("          <tr>\n");
			sb.append("               <th class=\"tdTitle\" colspan=\"2\">"+param.get("group")+"</th>\n");
			sb.append("          </tr>\n");
			//取规格项
			List<Map> object = (List<Map>) param.get("params");
			for (Map map : object) {
				sb.append("          <tr>\n");
				sb.append("               <td class=\"tdTitle\">"+map.get("k")+"</td>\n");
				sb.append("               <td>"+map.get("v")+"</td>\n");
				sb.append("          </tr>\n");
			}
		}
		sb.append("     </tbody>\n");
		sb.append("</table>");
		return sb.toString();
	}

	

}
