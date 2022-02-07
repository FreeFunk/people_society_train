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

	public List<SocietyStudentPractiseQuestionView> listByObjNew(SocietyStudentPractiseQuestionQuery query);

	/*判断学员是否关联该习题*/
    SocietyStudentPractiseQuestionView loadByStuIdAndQuestionId(@Param("studentId") String studentId,
																@Param("stuAndNodeId") String stuAndNodeId,
																@Param("questionId") String questionId);

    Integer countFraction(SocietyStudentPractiseQuestionQuery query);
	/*统计学员答对的正确个数*/
    int countByStudentAndNodeId(String studentAndNodeViewId);
	int countByStudentAndNodeIdNew(@Param("studentAndNodeViewId")String studentAndNodeViewId,
								   @Param("ascii")String ascii);


    Integer selectTotalNum(Map<String, String> map);

    void deleteByStuId(String id);

    void updateByStuId(Map<String, String> map);

	Integer countRight(Map<String,String> map);

	Integer countWrong(Map<String,String> map);
	Integer counNoAns(Map<String,String> map);

	Integer countRightNew(Map<String,String> map);

	Integer countWrongNew(Map<String,String> map);
	Integer counNoAnsNew(Map<String,String> map);


    void updateByNodeIdAndNodeName(@Param("nodeId") String nodeId, @Param("nodeName")String nodeName);

	//根据学生id 章节id 学生课程关联id 删除此课程章节下的所有学生选择的习题
    void deleteByStuIdAndNodeId(@Param("nodeId")String nodeId,
								@Param("stuCourseId")String stuCourseId, @Param("studentId")String studentId);

    void updateByCourseIdAndCourseName(Map<String, String> map);

    List<SocietyStudentPractiseQuestion> selectStuNodeId(@Param("stuNodeId")String stuNodeId);

    void updateByListId(@Param("list") List<SocietyStudentPractiseQuestion> questionList);

	void updateByListId2(@Param("list") List<SocietyStudentPractiseQuestionView> questionList,@Param("ascii")String ascii);

	void updateByListIdNew(@Param("list") List<SocietyStudentPractiseQuestion> questionList,
						   @Param("ascii") String ascii);

	Integer countQuestionLength(String stuAndCouId);

    Date selectByNodeId(String stuNodeId);

    List<SocietyStudentPractiseQuestion> selectByStudentId(String studentId);

	List<SocietyStudentPractiseQuestion> selectByStudentIdAscii(@Param("studentId")String studentId,
														   @Param("ascii")String ascii);

    void inserNewtList(@Param("questionList")List<SocietyStudentPractiseQuestion> questionList,
					   @Param("ascii")String ascii);

    List<SocietyStudentPractiseQuestionView> selectByStudentAndNodeId(
    		@Param("ownerStudentAndNodeId")String ownerStudentAndNodeId,@Param("ascii") String ascii);

    SocietyStudentPractiseQuestionView selectByStuNodeIdAndQuestionId(@Param("stuNodeId")String stuNodeId,
																	  @Param("questionId")String questionId,
																	  @Param("ascii")String ascii);

	SocietyStudentPractiseQuestionView selectByQuestionIdAndAscii(@Param("questionId")String questionId,
															  @Param("ascii")String ascii);

	List<SocietyStudentPractiseQuestionView> selectBySchoolIdAndNodeId(@Param("schoolId")String schoolId,
																	   @Param("oldNodeId") String oldNodeId,
																	   @Param("ascii")String ascii);
}