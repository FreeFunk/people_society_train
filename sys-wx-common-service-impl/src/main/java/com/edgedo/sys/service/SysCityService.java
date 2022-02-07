package com.edgedo.sys.service;

import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.SysCity;
import com.edgedo.sys.mapper.SysCityMapper;
import com.edgedo.sys.queryvo.SysCityQuery;
import com.edgedo.sys.queryvo.SysCityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysCityService {
	
	
	@Autowired
	private SysCityMapper mapper;

	
	public List<SysCityView> listPage(SysCityQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	public String insert(SysCity voObj) {
		voObj.setId(Guid.guid());
		mapper.insert(voObj);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	public String update(SysCity voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	public String updateAll(SysCity voObj) {
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
	public SysCity loadById(String id) {
		return mapper.selectById(id);
	}


	public List<SysCityView> listByProvinceId(String provinceId) {

		return mapper.listByProvinceId(provinceId);
	}
}
