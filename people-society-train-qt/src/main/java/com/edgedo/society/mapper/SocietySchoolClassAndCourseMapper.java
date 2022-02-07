package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolClassAndCourse;
import com.edgedo.society.queryvo.SocietySchoolClassAndCourseQuery;
import com.edgedo.society.queryvo.SocietySchoolClassAndCourseView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolClassAndCourseMapper  extends BaseMapper<SocietySchoolClassAndCourse>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassAndCourseView> listPage(SocietySchoolClassAndCourseQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassAndCourseView> listByObj(SocietySchoolClassAndCourseQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 根据班级查询课程id
	 * @Date 2020/5/26 11:49
	 **/
	List<String> selectCourseIdByClass(String classId);
	/**
	 * @Author WangZhen
	 * @Description 根据学校id和课程id查出一个班级的id
	 * @Date 2020/5/28 11:16
	 **/
    String selectLatestClassIdBySchAndCourse(
    		@Param("ownerSchoolId") String ownerSchoolId,
			@Param("courseId") String courseId);

}