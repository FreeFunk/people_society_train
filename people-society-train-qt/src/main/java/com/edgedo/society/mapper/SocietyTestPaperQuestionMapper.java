package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyTestPaperQuestion;
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
	 * @Author ZhangCC
	 * @Description 根据试卷查询试题
	 * @Date 2020/5/11 13:07
	 **/
	List<SocietyTestPaperQuestionView> selectQuestionListByPaper(String ownerTestPaperId);

	/**
	 * @Author ZhangCC
	 * @Description 根据试卷和题目类型查询数量
	 * @Date 2020/5/11 14:25
	 **/
	int countByPaperAndType(Map<String,Object> param);

}