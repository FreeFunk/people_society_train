package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentAndNode;
import com.edgedo.society.queryvo.SocietySchoolView;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import com.edgedo.society.queryvo.SocietyStudentAndNodeQuery;
import com.edgedo.society.queryvo.SocietyStudentAndNodeView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentAndNodeMapper  extends BaseMapper<SocietyStudentAndNode>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentAndNodeView> listPage(SocietyStudentAndNodeQuery query);
	List<SocietyStudentAndNodeView> selectByCourseNamelistPage(SocietyStudentAndNodeQuery societyStudentAndNodeQuery);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentAndNodeView> listByObj(SocietyStudentAndNodeQuery query);


	SocietyStudentAndNodeView selectByNodeIdAndStuId(Map<String, Object> map);
//	查出学生关联的小节id
    List<String> selectNodeIdList(SocietyStudentAndNodeQuery query);

	List<SocietyStudentAndNodeView> selectStudentCourse(SocietyStudentAndNodeQuery query);
	/**
	 *@Author:ZhaoSiDa
	 *@Description: 根据学员id和小节id查询学员和小节的关联
	 *@DateTime: 2020/5/30 9:03
	 */
    SocietyStudentAndNodeView loadByStuIdAndNodeId(@Param("studentId") String studentId,
												   @Param("stuAndCourseId") String stuAndCourseId,
												   @Param("nodeId") String nodeId);
	/*统计学员的课程所学的时长*/
    int sumStudyTimeLength(String stuAndCourseId);

	SocietyStudentAndNodeView selectByNodeIdAndStuIdOne(@Param("studentId")String studentId,
														@Param("courseAndStuId")String courseAndStuId,
														@Param("courseId")String courseId,
														@Param("nodeId")String nodeId);

	void updateByDataState(List<String> list);

    void deleteByStuId(String id);

    void updateByStuId(Map<String,String> map);

    void updateByNodeIdAndNodeName(@Param("nodeId")String nodeId, @Param("nodeName")String nodeName);

	Integer selectNodeTimeUse(SocietySchoolView societySchoolView);

    SocietyStudentAndNodeView selectByStuIdAndNodeIdAndStuCourseId(@Param("studentId")String studentId,
																   @Param("nodeId")String nodeId,
																   @Param("stuCourseId")String stuCourseId);

	int countFinishNodeOfStuCourse(String stuCourseId);

	void updateByFinishTime(String id);

    void updateByCourseIdAndCourseName(Map<String, String> map);

    void updateByStuCoIdAndNodeId(@Param("stuCourseId")String stuCourseId, @Param("nodeId")String nodeId);

    List<SocietyStudentAndNodeView> selectByStuIdAndCourseId(String stuAndCouId);


	List<SocietyStudentAndNodeView> selectByClassFacelistPage(SocietyStudentAndNodeQuery societyStudentAndNodeQuery);

	List<SocietyStudentAndNodeView> selectByClassFacelistObj(SocietyStudentAndNodeQuery societyStudentAndNodeQuery);

	List<SocietyStudentAndNodeView> classFacelistObj(String classId);

	//根据人脸不合格学生章节id 查出所有记录
	List<SocietyStudentAndNodeView> selectAllIsFiedStu(@Param("nodeList") List<String> nodeList);

    String selectNodeName(@Param("stuCoId")String stuCoId, @Param("ownerNodeName")String ownerNodeName);

	List<SocietyStudentAndNodeView> selectByStuIdAndCourseIdIsFiled(String stuCourseId);

    SocietyStudentAndNode selectByStuCouIdNewOnce(String stuAndCouId);

    Integer countStudyLength(String stuAndCouId);

    List<SocietyStudentAndNode> selectFinishTime();

    Integer selectByStudentId(String studentId);

    String selectByStuCouIdAndNodeId(@Param("nodeId")String nodeId, @Param("stuCourseId")String stuCourseId);

    int updateBatchIds(List<String> ids);

    Integer selectByStuCouId(String studentCourseId);

    SocietyStudentAndNode selectByStuCouIdDesc(String studentCourseId);
}