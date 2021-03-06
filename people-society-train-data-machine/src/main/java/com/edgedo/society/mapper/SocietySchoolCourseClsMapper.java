package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourseCls;
import com.edgedo.society.queryvo.SocietySchoolCourseClsQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseClsView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


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


    List<SocietySchoolCourseCls> listByMajorIdAndSchoolId(Map<String, String> map);

    void logicaDeletion(List<String> list);

    void updateByMajorId(@Param("majorId") String majorId,
						 @Param("majorName")String majorName);

    Integer count(String schoolId);
}