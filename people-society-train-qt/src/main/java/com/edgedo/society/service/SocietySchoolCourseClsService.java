package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourseCls;
import com.edgedo.society.mapper.SocietySchoolCourseClsMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseClsQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseClsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseClsService {
	
	
	@Autowired
	private SocietySchoolCourseClsMapper societySchoolCourseClsMapper;

	public List<SocietySchoolCourseClsView> listPage(SocietySchoolCourseClsQuery societySchoolCourseClsQuery){
		List list = societySchoolCourseClsMapper.listPage(societySchoolCourseClsQuery);
		societySchoolCourseClsQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourseCls societySchoolCourseCls) {
		societySchoolCourseCls.setId(Guid.guid());
		societySchoolCourseClsMapper.insert(societySchoolCourseCls);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourseCls societySchoolCourseCls) {
		societySchoolCourseClsMapper.updateById(societySchoolCourseCls);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourseCls societySchoolCourseCls) {
		societySchoolCourseClsMapper.updateAllColumnById(societySchoolCourseCls);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolCourseClsMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolCourseClsMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolCourseCls loadById(String id) {
		return societySchoolCourseClsMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据专业查询课程分类
	 * @Date 2020/5/5 19:38
	 **/
	public List<SocietySchoolCourseClsView> selectCourseClsByMajor(String ownerSchoolId,String ownerMajorId){
		Map<String,Object> param = new HashMap<>();
		param.put("ownerSchoolId",ownerSchoolId);
		param.put("ownerMajorId",ownerMajorId);
		return societySchoolCourseClsMapper.selectCourseClsByMajor(param);
	}
	

}
