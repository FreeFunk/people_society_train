package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourseNodeOption;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeOptionQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeOptionView;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolCourseNodeOptionMapper  extends BaseMapper<SocietySchoolCourseNodeOption>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseNodeOptionView> listPage(SocietySchoolCourseNodeOptionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseNodeOptionView> listByObj(SocietySchoolCourseNodeOptionQuery query);

	/**
	 * @Author WangZhen
	 * @Description 根据问题列表
	 * @Date 2020/5/8 21:10
	 **/
    List<SocietySchoolCourseNodeOptionView> selectOptionsByQuesList(List<SocietySchoolCourseNodeQuestionView> list);

	/**
	 * @Author WangZhen
	 * @Description 根据题目id获得所有正确答案的id
	 * @Date 2020/7/8 16:52
	 **/
    List<String> listRightAnswerIdsByQueId(String quesId);

}