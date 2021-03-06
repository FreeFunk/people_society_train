package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourseUseGloble;
import com.edgedo.society.queryvo.SocietySchoolCourseUseGlobleQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseUseGlobleView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolCourseUseGlobleMapper  extends BaseMapper<SocietySchoolCourseUseGloble>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseUseGlobleView> listPage(SocietySchoolCourseUseGlobleQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseUseGlobleView> listByObj(SocietySchoolCourseUseGlobleQuery query);


    Integer selectBySchoolId(String schoolId);

    int updateByList(List<String> ids);

    List<SocietySchoolCourseUseGlobleView> selectBySchoolObject(String schoolId);

    SocietySchoolCourseUseGloble selectBySchoolIdAndCourseIdOnce(@Param("ownerSchoolId") String ownerSchoolId,
																 @Param("courseId")String courseId);

    List<String> selectByCourseId(String schoolId);

    int selectSchoolIdAndCourseId(@Param("schoolId")String schoolId, @Param("courseId")String courseId);
}