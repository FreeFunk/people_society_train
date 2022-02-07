package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolTeacher;
import com.edgedo.society.queryvo.SocietySchoolTeacherQuery;
import com.edgedo.society.queryvo.SocietySchoolTeacherView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolTeacherMapper  extends BaseMapper<SocietySchoolTeacher>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolTeacherView> listPage(SocietySchoolTeacherQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolTeacherView> listByObj(SocietySchoolTeacherQuery query);

	/**
	 * 根据学校id查询所有讲师
	 * @param schoolId
	 * @return
	 */
    List<SocietySchoolTeacher> listBySchoolId(String schoolId);

    int updateVoId(List<String> ids);
}