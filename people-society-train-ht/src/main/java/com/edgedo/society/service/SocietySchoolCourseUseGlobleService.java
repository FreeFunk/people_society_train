package com.edgedo.society.service;
		
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourseUseGloble;
import com.edgedo.society.mapper.SocietySchoolCourseUseGlobleMapper;
import com.edgedo.society.queryvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseUseGlobleService {
	
	
	@Autowired
	private SocietySchoolCourseUseGlobleMapper societySchoolCourseUseGlobleMapper;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;

	public List<SocietySchoolCourseUseGlobleView> listPage(SocietySchoolCourseUseGlobleQuery societySchoolCourseUseGlobleQuery){
		List list = societySchoolCourseUseGlobleMapper.listPage(societySchoolCourseUseGlobleQuery);
		societySchoolCourseUseGlobleQuery.setList(list);
		return list;
	}

	public List<SocietySchoolCourseUseGlobleView> listPageNew(SocietySchoolCourseUseGlobleQuery societySchoolCourseUseGlobleQuery){
		List list = societySchoolCourseUseGlobleMapper.listPageNew(societySchoolCourseUseGlobleQuery);
		societySchoolCourseUseGlobleQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourseUseGloble societySchoolCourseUseGloble) {
		societySchoolCourseUseGloble.setId(Guid.guid());
		societySchoolCourseUseGlobleMapper.insert(societySchoolCourseUseGloble);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourseUseGloble societySchoolCourseUseGloble) {
		societySchoolCourseUseGlobleMapper.updateById(societySchoolCourseUseGloble);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourseUseGloble societySchoolCourseUseGloble) {
		societySchoolCourseUseGlobleMapper.updateAllColumnById(societySchoolCourseUseGloble);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolCourseUseGlobleMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolCourseUseGlobleMapper.updateByList(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolCourseUseGloble loadById(String id) {
		return societySchoolCourseUseGlobleMapper.selectById(id);
	}


	public Integer selectBySchoolId(String schoolId) {
		return societySchoolCourseUseGlobleMapper.selectBySchoolId(schoolId);
	}

	public List<SocietySchoolCourseUseGlobleView> selectBySchoolObject(String schoolId) {
		return societySchoolCourseUseGlobleMapper.selectBySchoolObject(schoolId);
	}

	public SocietySchoolCourseUseGloble selectBySchoolIdAndCourseIdOnce(String ownerSchoolId, String courseId) {
		return societySchoolCourseUseGlobleMapper.selectBySchoolIdAndCourseIdOnce(ownerSchoolId,courseId);
	}

	public List<String> selectByCourseId(String schoolId) {
		return societySchoolCourseUseGlobleMapper.selectByCourseId(schoolId);
	}
	public int selectSchoolIdAndCourseId(String schoolId, String courseId) {
		return societySchoolCourseUseGlobleMapper.selectSchoolIdAndCourseId(schoolId,courseId);
	}

	public List<String> selectByCourseIdInSchoolId(String schoolId) {
		return societySchoolCourseUseGlobleMapper.selectByCourseIdInSchoolId(schoolId);
	}
}
