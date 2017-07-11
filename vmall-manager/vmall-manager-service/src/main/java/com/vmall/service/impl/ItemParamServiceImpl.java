package com.vmall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmall.common.pojo.VmallResult;
import com.vmall.mapper.TbItemParamMapper;
import com.vmall.pojo.TbItemParam;
import com.vmall.pojo.TbItemParamExample;
import com.vmall.pojo.TbItemParamExample.Criteria;
import com.vmall.service.ItemParamService;

/**
 * 商品规格参数模板管理
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public VmallResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return VmallResult.ok(list.get(0));
		}
		
		return VmallResult.ok();
	}

	@Override
	public VmallResult insertItemParam(TbItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return VmallResult.ok();
	}

}
