package com.edgedo.sys.service;

import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.SysWxPayConfig;
import com.edgedo.sys.mapper.SysWxPayConfigMapper;
import com.edgedo.sys.queryvo.SysWxPayConfigQuery;
import com.edgedo.sys.queryvo.SysWxPayConfigView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SysWxPayConfigService {
	
	
	@Autowired
	private SysWxPayConfigMapper mapper;

	
	public List<SysWxPayConfigView> listPage(SysWxPayConfigQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	public String insert(SysWxPayConfig voObj) {
		voObj.setId(Guid.guid());
		mapper.insert(voObj);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	public String update(SysWxPayConfig voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	public String updateAll(SysWxPayConfig voObj) {
		mapper.updateAllColumnById(voObj);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	public int delete(String id) {
		
		return mapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public int deleteByIds(List<String> ids) {
		
		return mapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SysWxPayConfig loadById(String id) {
		return mapper.selectById(id);
	}
	

}
