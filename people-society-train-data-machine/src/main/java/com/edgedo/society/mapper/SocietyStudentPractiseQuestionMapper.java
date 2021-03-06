package com.edgedo.society.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentPractiseQuestion;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestionQuery;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentPractiseQuestionMapper  extends BaseMapper<SocietyStudentPractiseQuestion>{

	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentPractiseQuestionView> listPage(SocietyStudentPractiseQuestionQuery query);

	public List<SocietyStudentPractiseQuestionView> taskAndStudylistPage(SocietyStudentPractiseQuestionQuery query);

	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentPractiseQuestionView> listByObj(SocietyStudentPractiseQuestionQuery query);

	/*判断学员是否关联该习题*/
    SocietyStudentPractiseQuestionView loadByStuIdAndQuestionId(@Param("studentId") String studentId,
																@Param("stuAndNodeId") String stuAndNodeId,
																@Param("questionId") String questionId);

	SocietyStudentPractiseQuestionView loadByStuIdAndQuestionIdNew(@Param("studentId") String studentId,
																@Param("stuAndNodeId") String stuAndNodeId,
																@Param("questionId") String questionId,
																   @Param("ascii") String ascii);

    Integer countFraction(SocietyStudentPractiseQuestionQuery query);
	/*统计学员答对的正确个数*/
    int countByStudentAndNodeId(String studentAndNodeViewId);

    Integer selectTotalNum(Map<String, String> map);

    void deleteByStuId(String id);

    void updateByStuId(Map<String, String> map);

	Integer countRight(Map<String,String> map);

	Integer countWrong(Map<String,String> map);
	Integer counNoAns(Map<String,String> map);


    void updateByNodeIdAndNodeName(@Param("nodeId") String nodeId, @Param("nodeName")String nodeName);

	//根据学生id 章节id 学生课程关联id 删除此课程章节下的所有学生选择的习题
    void deleteByStuIdAndNodeId(@Param("nodeId")String nodeId,
								@Param("stuCourseId")String stuCourseId, @Param("studentId")String studentId);

    void updateByCourseIdAndCourseName(Map<String, String> map);

    List<SocietyStudentPractiseQuestion> selectStuNodeId(@Param("stuNodeId")String stuNodeId);

    void updateByListId(@Param("list") List<SocietyStudentPractiseQuestion> questionList);

	Integer countQuestionLength(String stuAndCouId);

    Date selectByNodeId(String stuNodeId);

    List<SocietyStudentPractiseQuestionView> selectByStuCouAndNodeId(@Param("ownerStuCourseId")String ownerStuCourseId,
																 @Param("nodeId") String nodeId);

	SocietyStudentPractiseQuestion selectByQuesionIdAndNodeIdAndStuCouId(@Param("questionId")String questionId,
																		 @Param("nodeId")String nodeId,
																		 @Param("ownerStuCourseId")String ownerStuCourseId);

	List<SocietyStudentPractiseQuestionView> selectByStuCouAndNodeId1(@Param("ownerStuCourseId")String ownerStuCourseId,
																	 @Param("nodeId") String nodeId,
																	 @Param("ascii")	 String ascii);
    Integer selectByNodeIdAndRight(String stuAndNodeId);
	Integer selectByNodeIdAndErr(String stuAndNodeId);
	Integer selectByNodeIdAndNull(String stuAndNodeId);

    void insertNew(@Param("question")SocietyStudentPractiseQuestionView question, @Param("ascii")String ascii);

	void deleteByIdNew(@Param("id")String id,@Param("ascii")String ascii);
}