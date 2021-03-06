package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestionOption;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionOptionQuery;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionOptionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentTestPaperQuestionOptionMapper  extends BaseMapper<SocietyStudentTestPaperQuestionOption>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentTestPaperQuestionOptionView> listPage(SocietyStudentTestPaperQuestionOptionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentTestPaperQuestionOptionView> listByObj(SocietyStudentTestPaperQuestionOptionQuery query);

	/**
	 * 学员选项表查出list
	 * @param map
	 * @return
	 */
    List<SocietyStudentTestPaperQuestionOption> selectBySchoolIdAndCourseIdAndPaperIdAndQuestId(Map<String, String> map);
	/*根据学员id和选项id查询*/
    SocietyStudentTestPaperQuestionOption loadByStuIdAndOptionId(@Param("studentId") String studentId,
																 @Param("testPaperId") String testPaperId,
																 @Param("questionOptionId") String questionOptionId);

    /*查询出正确或错误选项*/
	SocietyStudentTestPaperQuestionOption loadByStuQuestionId(@Param("stuQuestionId") String stuQuestionId,
															  @Param("isRight") String isRight);

	/*查询出学员选择的答案*/
	SocietyStudentTestPaperQuestionOption loadStuSelectOption(@Param("studentId") String studentId,
															  @Param("testPaperQuestionId") String testPaperQuestionId);

    void deleteByStuId(String id);

    void updateByCourseIdAndCourseName(Map<String, String> map);
}