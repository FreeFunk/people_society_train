package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.queryvo.SocietyStudentQuery;
import com.edgedo.society.queryvo.SocietyStudentView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentMapper  extends BaseMapper<SocietyStudent>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentView> listPage(SocietyStudentQuery query);

	public List<SocietyStudentView> classStudentListPage(SocietyStudentQuery query);

	List<SocietyStudentView> classNotInStudentListPage(SocietyStudentQuery query);

	List<SocietyStudentView> classYesInStudentListPage(SocietyStudentQuery query);
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentView> listByObj(SocietyStudentQuery query);

	void logicDelete(List<String> list);
	/**
	 * @Author WangZhen
	 * @Description 根据身份证号和学校id查询
	 * @Date 2020/7/8 11:01
	 **/
	SocietyStudentView selectVoByIdCardNum(@Param("idCardNum")String idCardNum,@Param("schoolId")String schoolId);
	/**
	 * @Author WangZhen
	 * @Description 根据手机号和学校id查询
	 * @Date 2020/7/8 11:01
	 **/
	SocietyStudentView selectVoByPhoneNum(@Param("phoneNum") String phoneNum,@Param("schoolId") String schoolId);

	Integer count(SocietyStudentQuery query);
	//学员统计
    int countStudentAllNum();
/*同一学校手机号和身份证号不重复不能导入*/
	int countByPhoneNumOrIdCardNum(Map<String, String> map);

    List<SocietyStudentView> selectAllStudent(String ownerSchoolId);

	List<SocietyStudentView> selectAllList();

    int selectVoPhoneAndCard(Map<String, String> map);

    String selectByPhoneAndIdCard(@Param("compId")String compId,
								  @Param("studentPhoneNum") String studentPhoneNum,
								  @Param("studentIdCardNum")String studentIdCardNum);

	SocietyStudent selectOneByPhoneNumAndSchool(
			@Param("studentPhoneNum") String studentPhoneNum,
			@Param("ownerSchoolId") String ownerSchoolId);

	//根据手机号  身份证号  查出表中多个学校的同一个学生
    List<SocietyStudent> selectByPhoneAndIdCardAll(@Param("studentPhoneNum")String studentPhoneNum,
												   @Param("studentIdCardNum")String studentIdCardNum);

    void deleteByStuId(String id);

	void updateByStuId(Map<String, String> map);

    List<String> selectByOrdAndCourseId(@Param("courseId")String courseId, @Param("schoolId")String schoolId);
	List<String> selectByImpAndCourseId(@Param("courseId")String courseId, @Param("schoolId")String schoolId);
	List<String> selectByCompAndCourseId(@Param("courseId")String courseId, @Param("schoolId")String schoolId);

	List<String> selectByOrdNullAndCourseId(@Param("courseId")String courseId,
											@Param("schoolId")String schoolId,
											@Param("classId")String classId);

    int countXianQuStudentAllNum(String xianquId);
	String findFaceImageByStudentId(String studentId);

	void updateByList(@Param("studentList")List<SocietyStudentView> studentList);
}