package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestionOption;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionOptionQuery;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionOptionView;
import org.apache.ibatis.annotations.Mapper;



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
	 * @Author ZhangCC
	 * @Description 根据学员问题查询选项
	 * @Date 2020/5/11 15:30
	 **/
	List<SocietyStudentTestPaperQuestionOptionView> selectOptionByQuestion(String studentQuestionId);

}