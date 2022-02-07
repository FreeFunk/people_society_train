package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourseNodeOption;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeOptionQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeOptionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


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
	
	List<String> selectOptionNameByQuestion(String questionId);

	List<SocietySchoolCourseNodeOption> selectOptionByQuestion(String questionId);

	List<SocietySchoolCourseNodeOptionView> selectQuestionId(String questionId);

    List<SocietySchoolCourseNodeOption> selectOptionByOptionList(Map<String, String> map);

    void deletenodeOptionList(List<SocietySchoolCourseNodeOption> list);

    void updateByNodeIdAndNodeName(@Param("nodeId") String nodeId,
								   @Param("nodeName")String nodeName);

    String selectByQuestionIdWrong(String questionId);


}