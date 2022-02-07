package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyTestPaper;
import com.edgedo.society.queryvo.SocietyTestPaperQuery;
import com.edgedo.society.queryvo.SocietyTestPaperView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyTestPaperMapper  extends BaseMapper<SocietyTestPaper>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyTestPaperView> listPage(SocietyTestPaperQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyTestPaperView> listByObj(SocietyTestPaperQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 根据课程和学校查询试卷
	 * @Date 2020/5/11 12:49
	 **/
	SocietyTestPaperView selectVoByCourseAndSchool(Map<String,Object> param);

}