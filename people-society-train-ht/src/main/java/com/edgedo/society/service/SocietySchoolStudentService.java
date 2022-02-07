package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolStudent;
import com.edgedo.society.mapper.SocietySchoolStudentMapper;
import com.edgedo.society.queryvo.SocietySchoolStudentQuery;
import com.edgedo.society.queryvo.SocietySchoolStudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolStudentService {
	
	
	@Autowired
	private SocietySchoolStudentMapper societySchoolStudentMapper;

	public List<SocietySchoolStudentView> listPage(SocietySchoolStudentQuery societySchoolStudentQuery){
		List list = societySchoolStudentMapper.listPage(societySchoolStudentQuery);
		societySchoolStudentQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolStudent societySchoolStudent) {
		societySchoolStudent.setId(Guid.guid());
		societySchoolStudentMapper.insert(societySchoolStudent);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolStudent societySchoolStudent) {
		societySchoolStudentMapper.updateById(societySchoolStudent);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolStudent societySchoolStudent) {
		societySchoolStudentMapper.updateAllColumnById(societySchoolStudent);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolStudentMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolStudentMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolStudent loadById(String id) {
		return societySchoolStudentMapper.selectById(id);
	}
	

}
