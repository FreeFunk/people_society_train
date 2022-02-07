package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolClassAndCourse;
import com.edgedo.society.mapper.SocietySchoolClassAndCourseMapper;
import com.edgedo.society.queryvo.SocietySchoolClassAndCourseQuery;
import com.edgedo.society.queryvo.SocietySchoolClassAndCourseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassAndCourseService {
	
	
	@Autowired
	private SocietySchoolClassAndCourseMapper societySchoolClassAndCourseMapper;

	public List<SocietySchoolClassAndCourseView> listPage(SocietySchoolClassAndCourseQuery societySchoolClassAndCourseQuery){
		List list = societySchoolClassAndCourseMapper.listPage(societySchoolClassAndCourseQuery);
		societySchoolClassAndCourseQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClassAndCourse societySchoolClassAndCourse) {
		societySchoolClassAndCourseMapper.insert(societySchoolClassAndCourse);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClassAndCourse societySchoolClassAndCourse) {
		societySchoolClassAndCourseMapper.updateById(societySchoolClassAndCourse);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClassAndCourse societySchoolClassAndCourse) {
		societySchoolClassAndCourseMapper.updateAllColumnById(societySchoolClassAndCourse);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolClassAndCourseMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolClassAndCourseMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolClassAndCourse loadById(String id) {
		return societySchoolClassAndCourseMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据班级查询课程id
	 * @Date 2020/5/26 11:49
	 **/
	public List<String> selectCourseIdByClass(String classId){
		return societySchoolClassAndCourseMapper.selectCourseIdByClass(classId);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据学校id和课程id查出一个班级的id
	 * @Date 2020/5/28 11:16
	 **/
	public String selectLatestClassIdBySchAndCourse(
			String ownerSchoolId, String courseId) {
		return societySchoolClassAndCourseMapper
				.selectLatestClassIdBySchAndCourse(ownerSchoolId,courseId);
	}

}
