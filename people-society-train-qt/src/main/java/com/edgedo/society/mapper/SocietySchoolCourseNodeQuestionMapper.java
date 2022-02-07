package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourseNodeQuestion;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolCourseNodeQuestionMapper  extends BaseMapper<SocietySchoolCourseNodeQuestion>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseNodeQuestionView> listPage(SocietySchoolCourseNodeQuestionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseNodeQuestionView> listByObj(SocietySchoolCourseNodeQuestionQuery query);

	/**
	 * @Author WangZhen
	 * @Description 根据课程章节id拿到习题的个数
	 * @Date 2020/7/10 11:11
	 **/
    int countQuesNumByNodeId(String ownerNodeId);

}