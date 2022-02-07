package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.mapper.SocietySchoolClassMapper;
import com.edgedo.society.queryvo.SocietySchoolClassQuery;
import com.edgedo.society.queryvo.SocietySchoolClassView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassService {
	
	
	@Autowired
	private SocietySchoolClassMapper societySchoolClassMapper;

	public List<SocietySchoolClassView> listPage(SocietySchoolClassQuery societySchoolClassQuery){
		List list = societySchoolClassMapper.listPage(societySchoolClassQuery);
		societySchoolClassQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClass societySchoolClass) {
		societySchoolClass.setId(Guid.guid());
		societySchoolClassMapper.insert(societySchoolClass);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClass societySchoolClass) {
		societySchoolClassMapper.updateById(societySchoolClass);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClass societySchoolClass) {
		societySchoolClassMapper.updateAllColumnById(societySchoolClass);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolClassMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolClassMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolClass loadById(String id) {
		return societySchoolClassMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询学校下面的所有班级
	 * @Date 2020/5/26 11:25
	 **/
	public List<SocietySchoolClassView> selectAllClassBySchool(String ownerSchoolId){
		return societySchoolClassMapper.selectAllClassBySchool(ownerSchoolId);
	}

}
