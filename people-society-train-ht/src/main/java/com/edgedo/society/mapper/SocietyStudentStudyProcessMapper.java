package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentStudyProcess;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessQuery;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentStudyProcessMapper  extends BaseMapper<SocietyStudentStudyProcess>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentStudyProcessView> listPage(SocietyStudentStudyProcessQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentStudyProcessView> listByObj(SocietyStudentStudyProcessQuery query);


    void updateByNodeIdAndNodeName(@Param("nodeId") String nodeId, @Param("nodeName")String nodeName);

    void updateByCourseIdAndCourseName(Map<String, String> map);

    List<SocietyStudentStudyProcess> selectByStuCouId(@Param("stuCouId")String stuCouId);

    void updateByListId(@Param("list")List<SocietyStudentStudyProcess> studyList);

	void updateByListId2(@Param("list")List<SocietyStudentStudyProcessView> studyList);

    List<SocietyStudentStudyProcessView> selectByNodeIdAndSchoolId(@Param("oldNodeId")String oldNodeId,
															@Param("schoolId")String schoolId);
}