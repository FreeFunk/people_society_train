package com.edgedo.society.mapper;

import java.util.List;
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
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentPractiseQuestionView> listByObj(SocietyStudentPractiseQuestionQuery query);

	/**
	 * @Author WangZhen
	 * @Description 根据学生的章节学习记录
	 * @Date 2020/5/9 13:54
	 **/
	SocietyStudentPractiseQuestionView selectByQueAndStuNode(
			@Param("quersionId") String quersionId,
			@Param("ownerStudentAndNodeId") String ownerStudentAndNodeId);

	SocietyStudentPractiseQuestionView selectByQueAndStuNodeNew(
			@Param("quersionId") String quersionId,
			@Param("ownerStudentAndNodeId") String ownerStudentAndNodeId,
			@Param("ascii") String ascii);

	/**
	 * @Author WangZhen
	 * @Description 根据学生学习节点来查询所有学生的答题情况
	 * @Date 2020/7/8 15:32
	 **/
    List<SocietyStudentPractiseQuestionView> listByOwnerStuNodeId(String stuNodeId);

	List<SocietyStudentPractiseQuestionView> listByOwnerStuNodeIdNew(@Param("stuNodeId")String stuNodeId,
																	 @Param("ascii")String ascii);
	/**
	 * @Author WangZhen
	 * @Description 设置所有节点的习题未答题
	 * @Date 2020/7/9 5:21
	 **/
    int updateAllStuNodeQuestUnAnswer(String stuNodeId);

	int updateAllStuNodeQuestUnAnswerNew(String stuNodeId,String ascii);

	void insertNew(@Param("question")SocietyStudentPractiseQuestionView question,
				   @Param("ascii")String ascii);

	void updateByIdNew(@Param("question")SocietyStudentPractiseQuestionView question, @Param("ascii")String ascii);

	int selectByStuNodeId(@Param("stuNodeId")String stuNodeId);

	/**
	 * @Author WangZhen
	 * @Description 删除节点的答题
	 * @Date 2020/7/9 5:21
	 **/
	int deleteStuQuestionByStuNode(@Param("stuNodeId")String stuNodeId,@Param("ascii")String ascii);

	void insertNewList(@Param("quetionList")List<SocietyStudentPractiseQuestionView> quetionList,
					   @Param("ascii")String ascii);
}