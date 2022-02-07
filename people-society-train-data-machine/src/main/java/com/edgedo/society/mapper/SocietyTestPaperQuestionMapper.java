package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyTestPaperQuestion;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionQuery;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionQuery;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyTestPaperQuestionMapper  extends BaseMapper<SocietyTestPaperQuestion>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyTestPaperQuestionView> listPage(SocietyTestPaperQuestionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyTestPaperQuestionView> listByObj(SocietyTestPaperQuestionQuery query);

	/**
	 * 考试题目表根据学校id 课程id 试卷id  查出一套试卷的所有题目
	 * @param map
	 * @return
	 */
    List<SocietyTestPaperQuestion> selectTestPaperAll(Map<String,String> map);

    List<SocietyTestPaperQuestionView> selectByAllQuestionlistPage(SocietyTestPaperQuestionQuery query);
	/*根据试卷id查询所有的试题*/
    List<SocietyTestPaperQuestionView> ListByTestPaperId(String testPaperId);
}