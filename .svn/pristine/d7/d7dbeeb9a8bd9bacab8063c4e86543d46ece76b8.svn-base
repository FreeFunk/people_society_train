package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestion;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionQuery;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionView;
import org.apache.ibatis.annotations.Mapper;



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
	 * @Author ZhangCC
	 * @Description 查询答对题目的数量
	 * @Date 2020/5/12 11:32
	 **/
	int countRightQuesByPaper(String ownerTestPaperId);

}