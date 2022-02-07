package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentStudyProcessFace;
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
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentStudyProcessFaceView> listByObj(SocietyStudentStudyProcessFaceQuery query);

	/**
	 * @Author WangZhen
	 * @Description 更新学习人脸匹配记录的业务id
	 * @Date 2020/5/6 19:20
	 **/
	public void updateFaceRecYwId(@Param("id") String id,@Param("ownerStudyProcessId") String ownerStudyProcessId);
	/**
	 * @Author WangZhen
	 * @Description 统计学生课程节点人脸数量
	 * @Date 2020/7/30 15:59
	 **/
    int selectCountByStuCourseAndNode(
    		@Param("stuCourseId") String stuCourseId,
			@Param("ownerNodeId") String courseNodeId);


	SocietyStudentStudyProcessFace selectByCourseNodeIdAndStuCourseIdStart(@Param("courseNodeId")String courseNodeId,
																		   @Param("stuCourseId")String stuCourseId);
	SocietyStudentStudyProcessFace selectByCourseNodeIdAndStuCourseIdEnd(@Param("courseNodeId")String courseNodeId,
																		 @Param("stuCourseId")String stuCourseId);

}