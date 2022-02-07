package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.mapper.SocietySchoolCourseMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseView;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseService {
	
	
	@Autowired
	private SocietySchoolCourseMapper societySchoolCourseMapper;

	public List<SocietySchoolCourseView> listPage(SocietySchoolCourseQuery societySchoolCourseQuery){
		List list = societySchoolCourseMapper.listPage(societySchoolCourseQuery);
		societySchoolCourseQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourse societySchoolCourse) {
		societySchoolCourse.setId(Guid.guid());
		societySchoolCourseMapper.insert(societySchoolCourse);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourse societySchoolCourse) {
		societySchoolCourseMapper.updateById(societySchoolCourse);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourse societySchoolCourse) {
		societySchoolCourseMapper.updateAllColumnById(societySchoolCourse);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolCourseMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolCourseMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolCourse loadById(String id) {
		return societySchoolCourseMapper.selectById(id);
	}

	/**
	 * 根据学校和课程id查询详情
	 * @param id
	 */
	public SocietySchoolCourse loadByIdAndSchool(String id,String ownerSchoolId) {
		Map<String,Object> param = new HashMap<>();
		param.put("id",id);
		param.put("ownerSchoolId",ownerSchoolId);
		return societySchoolCourseMapper.selectVoByIdAndSchool(param);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询该学校4条上新的课程
	 * @Date 2020/5/5 19:21
	 **/
	public List<SocietySchoolCourseView> selectLateLyFourCourse(String ownerSchoolId,String ownerMajorId){
		Map<String,Object> param = new HashMap<>();
		param.put("ownerSchoolId",ownerSchoolId);
		param.put("ownerMajorId",ownerMajorId);
		return societySchoolCourseMapper.selectLateLyFourCourse(param);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据课程分类查询3门循序渐进的课程
	 * @Date 2020/5/5 19:42
	 **/
	public List<SocietySchoolCourseView> selectThreeCourseByCourClsAndLevel(String ownerSchoolId,String courseClsId){
		Map<String,Object> param = new HashMap<>();
		param.put("ownerSchoolId",ownerSchoolId);
		param.put("courseClsId",courseClsId);
		return societySchoolCourseMapper.selectThreeCourseByCourClsAndLevel(param);
	}
	/**
	 * @Author WangZhen
	 * @Description 根据主键查询
	 * @Date 2020/7/15 9:10
	 **/
    public SocietySchoolCourse selectCourseById(String courseId) {
		return societySchoolCourseMapper.selectCourseById(courseId);
    }

    /**
     * @Author WangZhen
     * @Description  根据学员的关联课程查询课程
     * @Date 2020/7/15 9:50
     **/
	public List<SocietySchoolCourseView> listSchCourseByStuCourse(List<SocietyStudentAndCourseView> stuAndCourseList) {
		return societySchoolCourseMapper.listSchCourseByStuCourse(stuAndCourseList);
	}

}
