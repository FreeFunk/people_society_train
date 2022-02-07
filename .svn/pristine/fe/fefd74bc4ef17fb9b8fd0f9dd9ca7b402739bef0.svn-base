package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolClassCallName;
import com.edgedo.society.mapper.SocietySchoolClassCallNameMapper;
import com.edgedo.society.queryvo.SocietySchoolClassCallNameQuery;
import com.edgedo.society.queryvo.SocietySchoolClassCallNameView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassCallNameService {
	
	
	@Autowired
	private SocietySchoolClassCallNameMapper societySchoolClassCallNameMapper;

	public List<SocietySchoolClassCallNameView> listPage(SocietySchoolClassCallNameQuery societySchoolClassCallNameQuery){
		List list = societySchoolClassCallNameMapper.listPage(societySchoolClassCallNameQuery);
		societySchoolClassCallNameQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClassCallName societySchoolClassCallName) {
		societySchoolClassCallName.setId(Guid.guid());
		societySchoolClassCallNameMapper.insert(societySchoolClassCallName);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClassCallName societySchoolClassCallName) {
		societySchoolClassCallNameMapper.updateById(societySchoolClassCallName);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClassCallName societySchoolClassCallName) {
		societySchoolClassCallNameMapper.updateAllColumnById(societySchoolClassCallName);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolClassCallNameMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolClassCallNameMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolClassCallName loadById(String id) {
		return societySchoolClassCallNameMapper.selectById(id);
	}
	

}
