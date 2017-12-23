package com.tiangou.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiangou.common.pojo.EasyUIDataGridResult;
import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.mapper.TbItemParamMapper;
import com.tiangou.pojo.TbItem;
import com.tiangou.pojo.TbItemExample;
import com.tiangou.pojo.TbItemParam;
import com.tiangou.pojo.TbItemParamExample;
import com.tiangou.pojo.TbItemParamExample.Criteria;
import com.tiangou.service.ItemParamService;


/**
 * 商品规格参数模板service
 * <p>
 * Title: ItemParamServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 入云龙
 * @date 2015年8月16日上午10:45:40
 * @version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	
	@Override
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
		//分页处理
				PageHelper.startPage(page, rows);
				//执行查询
				TbItemParamExample example = new TbItemParamExample();
				List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
				//取分页信息
				PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
				//返回处理结果
				EasyUIDataGridResult result = new EasyUIDataGridResult(pageInfo.getTotal(),list);
				
				return result;
	}
	
	@Override
	public TiangouResult checkParam(long cid) {
		try {
			TbItemParamExample example = new TbItemParamExample();
			Criteria criteria = example.createCriteria();
			criteria.andItemCatIdEqualTo(cid);
			List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
			// 判断是否查询到结果
			if (null == list || list.isEmpty()) {
				return TiangouResult.ok();
			}
			return TiangouResult.ok(list.get(0));
		} catch (Exception e) {
			//return TiangouResult.build(500, ExceptionUtil.unwrapThrowable(null));
			return null;
		}
	}

	@Override
	public TiangouResult addItemParam(long cid, String paramData) {
		//创建规格模板pojo
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		//插入到数据库
		try {
			itemParamMapper.insert(tbItemParam);
		} catch(Exception e) {
			//return TiangouResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TiangouResult.ok();
	}

	@Override
	public TiangouResult getItemParemByCid(long cid) {
		
		//创建查询条件
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (null != list && !list.isEmpty()) {
			return TiangouResult.ok(list.get(0));
		}
		
		return TiangouResult.build(400, "此分类未定义规格模板");
	}

	@Override
	public TiangouResult insertItemParam(Long cid, String paramData) {
		//创建一个pojo
				TbItemParam itemParam = new TbItemParam();
				itemParam.setItemCatId(cid);
				itemParam.setParamData(paramData);
				itemParam.setCreated(new Date());
				itemParam.setUpdated(new Date());
				//插入记录
				itemParamMapper.insert(itemParam);
				return TiangouResult.ok();
	}



}
