package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourseCls;
import com.edgedo.society.queryvo.SocietySchoolCourseClsQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseClsView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolCourseClsMapper  extends BaseMapper<SocietySchoolCourseCls>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseClsView> listPage(SocietySchoolCourseClsQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseClsView> listByObj(SocietySchoolCourseClsQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 根据学校和专业查询课程分类
	 * @Date 2020/5/10 10:51
	 **/
	List<SocietySchoolCourseClsView> selectCourseClsByMajor(Map<String,Object> param);

}