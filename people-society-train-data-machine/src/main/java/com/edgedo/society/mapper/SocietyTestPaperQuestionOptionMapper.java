package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyTestPaperQuestionOption;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionOptionQuery;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionOptionView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyTestPaperQuestionOptionMapper  extends BaseMapper<SocietyTestPaperQuestionOption>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyTestPaperQuestionOptionView> listPage(SocietyTestPaperQuestionOptionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyTestPaperQuestionOptionView> listByObj(SocietyTestPaperQuestionOptionQuery query);

	List<String> selectOptionNameByQuestionId(String questionId);

	//公共选项表查出list
    List<SocietyTestPaperQuestionOption> selectBySchoolIdAndCourseIdAndPaperIdAndQuestId(Map<String, String> map);
    /*根据题目id查询所有的选择项*/
    List<SocietyTestPaperQuestionOption> selectOptionByQuestionId(String questionId);
}