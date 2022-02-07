package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolClass;
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


	/*根据班级查询关联的课程*/
    List<String> selectCourseIdByClass(String classId);
	List<String> selectCourseIdByClassNotId(@Param("classId")String classId,@Param("courseId")String[] courseId);
	List<String> selectCourseIdByClassYesId(@Param("classId")String classId,@Param("courseId")String[] courseId);

    int SumLesson(String classId);

	int SumCourseTimeLength(String classId);

    void deteleToClassIdAndSchoolIdAndId(@Param("list") List<String> list,
										 @Param("classId") String classId,
										 @Param("ownerSchoolId") String ownerSchoolId);

	void updateToClassIdAndSchoolIdAndId(@Param("list") List<String> list,
										 @Param("classId") String classId,
										 @Param("ownerSchoolId") String ownerSchoolId);

    String selectByClassId(String classId);

	SocietySchoolClassAndCourseView selectClassByOne(String classId);

	void deleteByIdOne(String id);

    void logicDelete(List<String> list);

    void updateByCourseId(Map<String, String> map);

    List<String> selectByCourseId(String courseId);

	List<SocietySchoolClassAndCourse> selectByAll();


	List<SocietySchoolClassAndCourseView> selectBySchoolIdAndCourseId(@Param("ownerSchoolId")String ownerSchoolId,
																	  @Param("courseId")String courseId);
}