package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestion;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionQuery;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentTestPaperQuestionMapper  extends BaseMapper<SocietyStudentTestPaperQuestion>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentTestPaperQuestionView> listPage(SocietyStudentTestPaperQuestionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentTestPaperQuestionView> listByObj(SocietyStudentTestPaperQuestionQuery query);


	/**
	 * 查出学员考试题目表所有已经答完的题目
	 * @param map
	 * @return
	 */
    List<SocietyStudentTestPaperQuestionView> selectByStudentTestPaperComplete(Map<String,String> map);
    /*根据学员id和试题id查询*/
    SocietyStudentTestPaperQuestion loadByStuIdAndQuestionId(@Param("studentId") String studentId,
															 @Param("testPaperId") String testPaperId,
															 @Param("questionId") String questionId);
	/*统计答对题目个数*/
	int countRightQuestionNumByType(@Param("studentId") String studentId,
									@Param("testPaperId") String testPaperId,
									@Param("type") String type);

    void deleteByStuId(String id);

    void updateByCourseIdAndCourseName(Map<String, String> map);

}