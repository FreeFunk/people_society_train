package com.edgedo.society.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietyStudentStudyProcessFace;
import com.edgedo.society.queryvo.SocietyStudentAndNodeView;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessFaceQuery;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessFaceView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentStudyProcessFaceMapper  extends BaseMapper<SocietyStudentStudyProcessFace>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentStudyProcessFaceView> listPage(SocietyStudentStudyProcessFaceQuery query);
	public List<SocietyStudentStudyProcessFaceView> listpageHuiFu(SocietyStudentStudyProcessFaceQuery query);

	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentStudyProcessFaceView> listByObj(SocietyStudentStudyProcessFaceQuery query);


    void updateByNodeIdAndNodeName(@Param("nodeId") String nodeId, @Param("nodeName")String nodeName);

    void updateByCourseIdAndCourseName(Map<String, String> map);

    int updateByIdLogin(@Param("list")List<String> list, @Param("user")User user, @Param("date")Date date);
	int retrieveFaceByIds(List<String> list);


    Integer selectByNodeIdAndStudentId(@Param("stuAndCouId")String stuAndCouId, @Param("nodeId")String nodeId);

    List<SocietyStudentStudyProcessFace> selectByStuCouId(@Param("stuCouId")String stuCouId);

    void updateByListId(@Param("list")List<SocietyStudentStudyProcessFace> faceList);

	void updateByListId2(@Param("list")List<SocietyStudentStudyProcessFaceView> faceList);

	String selectStuNodeAndFaceNum(@Param("stuCouId")String stuCouId,@Param("nodeId")String nodeId,
										 @Param("num") Integer num);

    List<SocietyStudentStudyProcessFace> selectByStuCouIdAndNodeId(@Param("stuCourseId")String stuCourseId,
																   @Param("nodeId") String nodeId);

    List<SocietyStudentStudyProcessFace> selectByStuCouIdRand(String stuAndCouId);

    List<SocietyStudentStudyProcessFaceView> selectBySchoolIdAndNodeId(@Param("schoolId")String schoolId,
																	   @Param("oldNodeId")String oldNodeId);
}