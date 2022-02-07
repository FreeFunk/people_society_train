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

	SocietySchoolClassAndStudentView selectVoByClassAndStudent(Map<String,Object> param);

	int deleteVoByClassAndStudent(Map<String,Object> param);

	List<String> selectStudentIdByClass(String classId);

	int countByClassAndLearnNotFinished(String classId);

	List<SocietySchoolClassAndStudentView> selectBySchoolNamelistPage(SocietySchoolClassAndStudentQuery query);

	List<SocietySchoolClassAndStudentView> selectBySchoolAndClassBySchoolIdAndClassIdlistPage(SocietySchoolClassAndStudentQuery query);

    List<SocietySchoolClassAndStudentView> selectNotRelationList(Map<String, String> map);
	//查询该班级下的所有的学生
    List<SocietySchoolClassAndStudentView> selectVoByClassId(String classId);
	//根据班级ID统计班级的人数
    int countStudentByClassId(String classId);
	/*根据学校id和班级id查询完成培训的人数*/
    int countFinishedStuNum(@Param("schoolId") String schoolId,
							@Param("classId") String classId);

    int selectVoClassId(String classId);

    int selectVoClassAndStudentName(@Param("classId")String classId,
									@Param("studentName")String studentName,
									@Param("studentIdCardNum")String studentIdCardNum);

    List<String> selectBySchoolIdAndClassId(String schoolId, List<String> classId);

    List<SocietySchoolClassAndStudent> selectBySchoolIdVoClassId(@Param("classId")String classId,
																 @Param("schoolId")String schoolId);

	/**
	 * 根据班级id 学生id 查询
	 * @param classId
	 * @param studentOldId
	 * @return
	 */
	int selectBySchoolIdVoClassIdOne(@Param("classId")String classId,
									 @Param("studentOldId")String studentOldId);


	public List<SocietySchoolClassAndStudentView> selectByStuIdAndSchoolIdlistPage(SocietySchoolClassAndStudentQuery query);
	public List<SocietySchoolClassAndStudentView> selectByClassIdStuIdAndSchoolIdlistPage(SocietySchoolClassAndStudentQuery query);

    void deleteByStuId(String id);

    void updateByStuId(Map<String, String> map);

    String selectByClassIdAndStudentIdAndSchoolId(@Param("classId")String classId,
												  @Param("studentId")String studentId,
												  @Param("schoolId")String schoolId);

    List<SocietySchoolClassAndStudentView> selectByAll();

    void updateByMajorId(@Param("majorId")String majorId,
						 @Param("majorName")String majorName);

    void updateByCourseId(Map<String, String> map);

    Integer selectCountByClassId(String classId);

    List<String> selectByRandStuId(String classId);

    List<SocietySchoolClassAndStudent> selectByClassId(Map<String,String> map);

    List<String> selectByClassIdRandTenStudent(String classId);

    List<String> selectByListClassId(@Param("classId")List<String> classId);

	List<String> selectByStudentIdVoClassId(String stuId);

	void updateByIdList(@Param("stuList")List<SocietySchoolClassAndStudentView> stuList);

	Integer selectByClassAndStudentNum();
}