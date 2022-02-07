package com.edgedo.sys.service;

import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.SysDwzStatic;
import com.edgedo.sys.mapper.SysDwzStaticMapper;
import com.edgedo.sys.queryvo.SysDwzStaticQuery;
import com.edgedo.sys.queryvo.SysDwzStaticView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SysDwzStaticService {
	
	
	@Autowired
	private SysDwzStaticMapper mapper;

	
	public List<SysDwzStaticView> listPage(SysDwzStaticQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	public String insert(SysDwzStatic voObj) {
		voObj.setId(Guid.guid());
		mapper.insert(voObj);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	public String update(SysDwzStatic voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	public String updateAll(SysDwzStatic voObj) {
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
	public SysDwzStatic loadById(String id) {
		return mapper.selectById(id);
	}
	

}
