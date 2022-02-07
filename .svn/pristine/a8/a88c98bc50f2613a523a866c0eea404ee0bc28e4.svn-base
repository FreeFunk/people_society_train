package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.queryvo.SocietySchoolCourseQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseView;
import com.edgedo.sys.entity.Dtree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolCourseMapper  extends BaseMapper<SocietySchoolCourse>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseView> listPage(SocietySchoolCourseQuery query);

	public List<SocietySchoolCourseView> listPageCourseGloble(SocietySchoolCourseQuery query);
	public List<SocietySchoolCourseView> listPageCourseGlobleNew(SocietySchoolCourseQuery query);

	//班级课程列表
	public List<SocietySchoolCourseView> classCourseListPage(SocietySchoolCourseQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseView> listByObj(SocietySchoolCourseQuery query);

/*逻辑删除*/
    void updateByIds(List<String> ids);

    List<SocietySchoolCourseView> classNotInCourseListPage(SocietySchoolCourseQuery query);
	List<SocietySchoolCourseView> courseTablepage(SocietySchoolCourseQuery query);
	List<SocietySchoolCourseView> classYesInCourseListPage(SocietySchoolCourseQuery query);
	Integer count(SocietySchoolCourseQuery query);
	//课程统计
    int countCourseAllNum();

    void updateByIdNew(String id);

    List<SocietySchoolCourseView> selectAllCourse(String schoolId);

    int selectBySchoolId(String ownerSchoolId);

    int selectBySchoolIdAndTeacher(@Param("schoolId") String schoolId,
								   @Param("id")String id);

    List<SocietySchoolCourseView> listBySchoolId(String schoolId);

	List<SocietySchoolCourse> selectAll();

    List<SocietySchoolCourse> listMajorId(@Param("schoolId")String schoolId, @Param("majorId")String majorId);


	List<SocietySchoolCourseView> selectCourseAllIsNoSchool(@Param("schoolId")String schoolId);
	List<SocietySchoolCourseView> selectCourseAllIsSchool(@Param("schoolId")String schoolId);

    void updateByMajorId(@Param("majorId")String majorId,
						 @Param("majorName")String majorName);

    void updateByClsId(@Param("clsId")String clsId,
					   @Param("clsName")String clsName,
					   @Param("majorId")String majorId,
					   @Param("majorName")String majorName);

    void updateByTeacherId(@Param("teacherId")String teacherId,
						   @Param("teacherName")String teacherName);

    int selectSchoolIdAndCourseId(@Param("schoolId")String schoolId, @Param("courseId")String courseId);

    List<SocietySchoolCourseView> selectBySchoolIdCourseId(@Param("ownerSchoolCourseId")String ownerSchoolCourseId);

    int selectBySchoolIdAndCourseId(@Param("nowSchoolId")String nowSchoolId, @Param("courseId")String courseId);

    int selectByOwnerCourseId(String courseId);

    void updateByIsOpen(@Param("courseId")String courseId, @Param("isOpen")String isOpen);

    List<String> listByOwnerSchoolId(String schoolId);

    List<String> selectByCourseIdList(String schoolId);

    SocietySchoolCourse selectBySchoolIdAndOwnerCourseId(@Param("ownerSchoolId")String ownerSchoolId,
														 @Param("ownerCourseId")String ownerCourseId);
}