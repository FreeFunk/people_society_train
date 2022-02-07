package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentTestPaper;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuery;
import com.edgedo.society.queryvo.SocietyStudentTestPaperView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentTestPaperMapper  extends BaseMapper<SocietyStudentTestPaper>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentTestPaperView> listPage(SocietyStudentTestPaperQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentTestPaperView> listByObj(SocietyStudentTestPaperQuery query);

	/*根据学员id和试卷id查询*/
    SocietyStudentTestPaper loadByStuIdAndTestPaperId(@Param("studentId") String studentId,
													  @Param("testPaperId") String testPaperId);

    void deleteByStuId(String id);

    void updateByStuId(Map<String, String> map);

    String selectByStuIdAndCourseIdAndSchoolId(@Param("courseId")String courseId,
											   @Param("studentId")String studentId,
											   @Param("ownerSchoolId")String ownerSchoolId);

	SocietyStudentTestPaper selectByStuIdAndCourseIdAndSchoolIdOnce(@Param("courseId")String courseId,
											   @Param("studentId")String studentId,
											   @Param("ownerSchoolId")String ownerSchoolId);

    void updateByCourseIdAndCourseName(Map<String, String> map);

    List<SocietyStudentTestPaper> selectByStuIdAndCourseIdAndSchoolIdList(@Param("studentId")String studentId,
																		  @Param("courseId")String courseId,
																		  @Param("ownerSchoolId")String ownerSchoolId);

}