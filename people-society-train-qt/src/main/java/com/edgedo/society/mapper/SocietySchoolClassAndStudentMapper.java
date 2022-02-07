package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolClassAndStudent;
import com.edgedo.society.queryvo.SocietySchoolClassAndStudentQuery;
import com.edgedo.society.queryvo.SocietySchoolClassAndStudentView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolClassAndStudentMapper  extends BaseMapper<SocietySchoolClassAndStudent>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassAndStudentView> listPage(SocietySchoolClassAndStudentQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassAndStudentView> listByObj(SocietySchoolClassAndStudentQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 根据学员和学校查询课程信息
	 * @Date 2020/5/10 10:50
	 **/
	SocietySchoolClassAndStudentView selectClassByStuId(Map<String,Object> param);

	/**
	 * @Author ZhangCC
	 * @Description 根据班级ID统计班级的人数
	 * @Date 2020/5/26 14:08
	 **/
	int countStudentByClassId(String classId);

	/**
	 * @Author WangZhen
	 * @Description 根据学生id和班级id 统计数量，看是否存在这个学生
	 * @Date 2020/5/28 11:35
	 **/
	int countByStuAndClassId(
			@Param("studentId") String studentId,
			@Param("classId") String classId);
}