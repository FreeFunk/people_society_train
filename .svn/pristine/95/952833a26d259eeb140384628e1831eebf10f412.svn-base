package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolStudent;
import com.edgedo.society.queryvo.SocietySchoolStudentQuery;
import com.edgedo.society.queryvo.SocietySchoolStudentView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolStudentMapper  extends BaseMapper<SocietySchoolStudent>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolStudentView> listPage(SocietySchoolStudentQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolStudentView> listByObj(SocietySchoolStudentQuery query);



}