package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentPractiseQuestOption;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestOptionQuery;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestOptionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentPractiseQuestOptionMapper  extends BaseMapper<SocietyStudentPractiseQuestOption>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentPractiseQuestOptionView> listPage(SocietyStudentPractiseQuestOptionQuery query);

	public List<SocietyStudentPractiseQuestOptionView> selectByQuesionIdlistPage(SocietyStudentPractiseQuestOptionQuery query);
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentPractiseQuestOptionView> listByObj(SocietyStudentPractiseQuestOptionQuery query);


    List<SocietyStudentPractiseQuestOption> selectByQuesionId(
			@Param("quersionId") String quersionId,
			@Param("ownerNodeId") String ownerNodeId,
			@Param("studentId") String studentId);
	/*判断是否已经有该选项*/
    SocietyStudentPractiseQuestOption loadByStuIdAndOptionId(@Param("studentId") String studentId,
															   @Param("stuAndNodeId")String stuAndNodeId,
															   @Param("nodeOptionId")String nodeOptionId);
	/*查询出题目所关联的正确或者错误答案*/
	SocietyStudentPractiseQuestOption loadByStuQuestionId(@Param("stuQuestionId") String stuQuestionId,
														  @Param("isRight") String isRight);

	List<SocietyStudentPractiseQuestOptionView> selectByOneOption(Map<String, String> map);
	List<SocietyStudentPractiseQuestOptionView> selectByOneOptionNew(Map<String, String> map);

	SocietyStudentPractiseQuestOptionView selectByOneOptionOnce(Map<String, String> map);

	SocietyStudentPractiseQuestOptionView selectByOneOptionOnceNew(Map<String, String> map);

    Integer countRight(Map<String, String> map);

	Integer countWrong(Map<String, String> map);

    void deleteByStuId(String id);

    void updateByNodeIdAndNodeName(@Param("nodeId")String nodeId, @Param("nodeName")String nodeName);

    //根据学生id 章节id 学生课程关联id 删除此课程章节下的所有学生选择的习题选项
    void deleteByStuIdAndNodeId(@Param("nodeStuId")String nodeStuId);

    List<SocietyStudentPractiseQuestOption> selectByStuNodeAndStuCouId(@Param("stuCouId")String stuCouId,
																		   @Param("stuNodeId")String stuNodeId);

    void updateByListId(@Param("list")List<SocietyStudentPractiseQuestOption> optionList);

    List<SocietyStudentPractiseQuestOption> selectByQuestionIdAndNodeStuId(@Param("stuNodeId")String stuNodeId,
																		   @Param("questionId")String questionId);

    void inserNewtList(@Param("allOptionList")List<SocietyStudentPractiseQuestOption> allOptionList,
					   @Param("ascii")String ascii);
}