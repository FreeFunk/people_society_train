package com.edgedo.society.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.entity.SocietyStudentAndCourse;
import com.edgedo.society.queryvo.SocietySchoolView;
import com.edgedo.society.queryvo.SocietyStudentAndCourseQuery;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentAndCourseMapper  extends BaseMapper<SocietyStudentAndCourse>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentAndCourseView> listPage(SocietyStudentAndCourseQuery query);
	public List<SocietyStudentAndCourseView> selectBySchoolAndClasslistPage(SocietyStudentAndCourseQuery query);


	public Integer selectByCourseStudyNum(String courseId);

	public List<SocietyStudentAndCourseView> forclassadminlistPage(SocietyStudentAndCourseQuery query);

	List<SocietyStudentAndCourseView> selectBySchoolNamelistPage(SocietyStudentAndCourseQuery societySchoolClassAndCourseQuery);

	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentAndCourseView> listByObj(SocietyStudentAndCourseQuery query);
	public List<SocietyStudentAndCourseView> listByObjIsFiled(SocietyStudentAndCourseQuery query);
	public List<SocietyStudentAndCourseView> listByExcObjClassAdmin(SocietyStudentAndCourseQuery query);

	public List<SocietyStudentAndCourseView> listByExcRenSheObj(SocietyStudentAndCourseQuery query);

    String selectByOwnerStudentAndNodeId(@Param("ownerStudentAndNodeId") String ownerStudentAndNodeId);

	/*批量插入关联*/
    void insertCourseAndStudent(List<SocietyStudentAndCourse> studentAndCourseList);
	//统计课程的培训人
    int countByCourseId(String courseId);
/*统计学员是否关联该班级课程*/
    int countByMap(Map<String, Object> map);

	void updateStuAndCourse(Map<String, Object> map);

	//删除该该班级所有学生和课程的关联
    void updateByClassIdAndCourseId(@Param("list") List<String> list,
									@Param("classId") String classId);
	/*统计课程的培训人数*/
    int countTotalStuNum(String courseId);

    /**
     * @Author ZhangCC
     * @Description 根据学员和课程查询一条关联
     * @Date 2020/6/1 10:13
     **/
    SocietyStudentAndCourseView selectVoByStudentAndCourse(@Param("studentId") String studentId,@Param("courseId") String courseId);

    /**
     *@Author:ZhaoSiDa
     *@Description: 根据班级id和学员id查询学员关联的课程
     *@DateTime: 2020/5/29 16:42
     */
    List<SocietyStudentAndCourseView> listByClassIdAndStuId(@Param("classId")String classId, @Param("studentId")String studentId);
	/*根据学校id和课程id，统计完成课程的人数*/
    int countFinishedCourseStuNum(@Param("schoolId") String schoolId,
								  @Param("courseId") String courseId);

	void updateVoClassIdAndCourseId(@Param("className")String className,
									@Param("ownMajorId")String ownMajorId,
									@Param("ownMajorName")String ownMajorName,
									@Param("classId")String classId,
									@Param("courseId")String courseId);

    String selectStuAndClassId(@Param("classId")String classId, @Param("studentId")String studentId);

    SocietyStudentAndCourse selectByStudentIdAndClassId(@Param("classId")String classId,
														@Param("studentId")String studentId
														,@Param("schoolId")String schoolId);

    SocietyStudentAndCourse findStudentAndCourseByClassIdAndStudentId(Map<String,String> map);
    int countFinishedClassStuNum(@Param("classId")String classId);

	List<String> selectByAllId(Map<String, Object> param);

    void deleteByStuId(String id);

    void updateByStuId(Map<String, String> map);

    int countCourseStuNum(String schoolId);

    List<SocietyStudentAndCourse> countIsNotCertifi();

    List<SocietyStudentAndCourseView> selectByStuIdAndSchoolId(@Param("studentId")String studentId,
														 @Param("schoolId")String schoolId);

    void updateByClassAndStudentId(@Param("classId")String classId,
								   @Param("studentId")String studentId,
								   @Param("schoolId")String schoolId,
								   @Param("classAndStudentId")String classAndStudentId);

	void updateOnceId(SocietyStudentAndCourse societyStudentAndCourse);

    void updateByMajorId(@Param("majorId")String majorId,
						 @Param("majorName")String majorName);

    void updateByCourseId(@Param("courseId")String courseId,
						  @Param("ownerSchoolId")String ownerSchoolId,
						  @Param("courseStudyNum")Integer courseStudyNum,
						  @Param("courseIsNeedFaceContrast")String courseIsNeedFaceContrast);

    SocietyStudentAndCourseView selectSchAndClaAndCouAndStu(@Param("schoolId")String schoolId,
															@Param("classId")String classId,
															@Param("courseId")String courseId,
															@Param("studentId")String studentId);

    Integer selectByCourseIdAndSchoolId(@Param("schoolId")String schoolId, @Param("ownerCourseId")String ownerCourseId);

	Integer selectByUseCourseIdAndSchoolId(@Param("schoolId")String schoolId, @Param("ownerCourseId")String ownerCourseId);

	Integer selectNodeTime(SocietySchoolView societySchoolView);


    void updateFinishTime(String stuCourseId);

    void updateByCourseIdAndCourseName(Map<String, String> map);

    List<SocietyStudentAndCourseView> selectByClassId(String classId);

    void updateByOrdStu(@Param("studenIdList") List<String> studenIdList,
						@Param("courseId") String courseId,
						@Param("courseStudyNum") Integer courseStudyNum,
						@Param("courseIsNeedFaceContrast") String courseIsNeedFaceContrast);

    Integer selectByClassIdAndStudyInfo(String classId);

	SocietyStudentAndCourse selectByStuIdAndCourseId(@Param("stuId")String stuId,@Param("courseId") String courseId);

    void updateByClassIdList(@Param("list")List<SocietyStudentAndCourseView> list,
							 @Param("societySchoolClass")SocietySchoolClass societySchoolClass);

    int selectByCourseIdAndTime(Map<String, String> map);

	int selectByCourseIdAndTimeNew(String courseId);

    List<SocietyStudentAndCourseView> selectByCourseIdAndSchoolId2(@Param("courseIdOld")String courseIdOld,
																   @Param("schoolId")String schoolId);

	void updateList(@Param("stuCouList")List<SocietyStudentAndCourseView> stuCouList);
	List<SocietyStudentAndCourseView> selectByClassIdVoFinish(String classId);
	List<SocietyStudentAndCourseView> selectByClassIdVoNoFinish(String classId);
}