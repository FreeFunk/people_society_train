package com.edgedo.society.mapper;

import java.util.List;
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

	/**
	 * @Author ZhangCC
	 * @Description 根据试题查询试题选项
	 * @Date 2020/5/11 13:40
	 **/
	List<SocietyTestPaperQuestionOptionView> selectOptionListByQuestion(String paperQuestionId);

}