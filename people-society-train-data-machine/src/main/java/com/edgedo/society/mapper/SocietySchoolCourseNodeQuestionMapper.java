package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourseNodeQuestion;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionView;
import com.edgedo.society.queryvo.SocietyStudentAndNodeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


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
	 * 查询所有相关的小节的题目
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseNodeQuestionView> selectByQuesionAllIdlistPage(SocietySchoolCourseNodeQuestionQuery query);

	/**
	 * 根据课程id与习题类型随从查询n条记录，试卷管理生成试题使用
	 * @param map
	 * @return
	 */
	public List<SocietySchoolCourseNodeQuestionView> selectQuestionsForRound(Map map);

	List<SocietySchoolCourseNodeQuestionView> selectByCourseIdAndNodeIdlistPage
			(SocietySchoolCourseNodeQuestionQuery query);
    /*统计小节下面习题的数量*/
    int countQuestionNumByNodeId(String nodeId);

    int selectByCourseIdAndNodeId(Map<String,String> map);

    /**
     *@Author:ZhaoSiDa
     *@Description: 根据小节ID查询小节下面的所有的习题
     *@DateTime: 2020/5/30 9:32
     */
    List<SocietySchoolCourseNodeQuestionView> listByNodeId(String nodeId);

    Integer selectQuestionNum(String ownerNodeId);

    List<SocietySchoolCourseNodeQuestionView> selectByCourseIdAndNodeIdList(Map<String, String> map);

    void updateByMajorId(@Param("majorId") String majorId,
						 @Param("majorName") String majorName);


	void updateByClsId(@Param("clsId")String clsId,
					   @Param("clsName")String clsName,
					   @Param("majorId")String majorId,
					   @Param("majorName")String majorName);

    void updateByNodeIdAndNodeName(@Param("nodeId")String nodeId, @Param("nodeName")String nodeName);

    void updateByCourseIdAndCourseName(Map<String, String> map);

	String selectCourseIdAndquesName(@Param("questionName")String questionName,
									 @Param("courseId")String courseId,
									 @Param("nodeId")String nodeId);

    Integer selectByNodeId(String nodeId);
}