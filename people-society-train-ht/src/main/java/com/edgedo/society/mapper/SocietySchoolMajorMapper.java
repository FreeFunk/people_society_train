package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolMajor;
import com.edgedo.society.queryvo.SocietySchoolCourseUseGlobleView;
import com.edgedo.society.queryvo.SocietySchoolMajorQuery;
import com.edgedo.society.queryvo.SocietySchoolMajorView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolMajorMapper  extends BaseMapper<SocietySchoolMajor>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolMajorView> listPage(SocietySchoolMajorQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolMajorView> listByObj(SocietySchoolMajorQuery query);

	void logicDelete(List<String> list);
	
	List<SocietySchoolMajorView> listBySchoolId(String ownerSchoolId);

	Integer count(SocietySchoolMajorQuery query);


	SocietySchoolMajorView selectByIdView(String ownerMajorId);

	Integer countAll(@Param("schoolId") String schoolId);

    List<SocietySchoolMajorView> selectBySchoolList(@Param("schoolIdList")List<String> schoolIdList);
}