package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.queryvo.SocietySchoolCourseQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseView;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolCourseMapper  extends BaseMapper<SocietySchoolCourse>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseView> listPage(SocietySchoolCourseQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseView> listByObj(SocietySchoolCourseQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 根据学校和主键查询课程
	 * @Date 2020/5/10 10:52
	 **/
	SocietySchoolCourse selectVoByIdAndSchool(Map<String,Object> param);

	/**
	 * @Author ZhangCC
	 * @Description 根据学校和专业查询最新的4门课程
	 * @Date 2020/5/10 10:52
	 **/
	List<SocietySchoolCourseView> selectLateLyFourCourse(Map<String,Object> param);

	/**
	 * @Author ZhangCC
	 * @Description 根据学校和专业查询3门进阶课程
	 * @Date 2020/5/10 10:53
	 **/
	List<SocietySchoolCourseView> selectThreeCourseByCourClsAndLevel(Map<String,Object> param);
	/**
	 * @Author WangZhen
	 * @Description 根据主键加载学校课程
	 * @Date 2020/7/15 9:13
	 **/
    SocietySchoolCourse selectCourseById(String id);

    /**
     * @Author WangZhen
     * @Description 根据学员课程列表查询学校课程
     * @Date 2020/7/15 10:01
     **/
    List<SocietySchoolCourseView> listSchCourseByStuCourse(List<SocietyStudentAndCourseView> stuAndCourseList);
}