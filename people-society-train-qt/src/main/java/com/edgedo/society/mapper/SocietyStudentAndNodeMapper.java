package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentAndNode;
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
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentAndNodeView> listByObj(SocietyStudentAndNodeQuery query);

	/**
	 * @Author WangZhen
	 * @Description 根据学生课程关联id获得 学生章节学习情况
	 * @Date 2020/5/4 11:51
	 **/
    List<SocietyStudentAndNodeView> selectNodeHisByOwnerCorse(String ownerStuCourseId);

    /**
     * @Author WangZhen
     * @Description 根据学生的课程id 和 课程的节点获得学生的学习记录节点
     * @Date 2020/5/6 18:36
     **/
    SocietyStudentAndNode selectStuCourseNodeByStuCourseIdAndCourseNodeId(
			@Param("ownerStuCourseId") String ownerStuCourseId,
			@Param("nodeId") String nodeId);

	/**
	 * @Author WangZhen
	 * @Description 更新学习记录的学习时长
	 * @Date 2020/5/6 20:10
	 **/
    int updateStuNodeStudyTime(
    		@Param("id") String id,@Param("studyTimeLength") int studyTimeLength);

	/**
	 * @Author WangZhen
	 * @Description 更新学习记录的学习时长
	 * @Date 2020/5/6 20:10
	 **/
	int updateStuNodeAddSecond(
			@Param("id") String id,@Param("studyTimeLength") int studyTimeLength);

	/**
	 * @Author WangZhen
	 * @Description 设置学习结束
	 * @Date 2020/5/7 9:22
	 **/
	int finishStuCourseNode(SocietyStudentAndNode studentAndNode);
	/**
	 * @Author WangZhen
	 * @Description 统计已经完成的课程章节数
	 * @Date 2020/5/7 9:37
	 **/
	int countFinishNodeOfStuCourse(String stuCourseId);
	/**
	 * @Author WangZhen
	 * @Description 学习读秒时长不够补时长
	 * @Date 2020/5/7 10:13
	 **/
    int finishStuCourseNodeLessTime(SocietyStudentAndNode studentAndNode);

	/**
	 * @Author WangZhen
	 * @Description 更新学习记录的学习时长 同时更细上次学到第几秒
	 * @Date 2020/5/7 20:23
	 **/
    int updateStuNodeStudyTimeWithLastSec(
    		  @Param("id") String id,
			  @Param("studyTimeLength") int studyTimeLength,
			  @Param("lastLearnLocation") Integer lastLearnLocation
	);

	/**
	 * @Author WangZhen
	 * @Description 更新学习记录的学习时长,同时更细上次学到第几秒
	 * @Date 2020/5/7 20:27
	 **/
	int updateStuNodeAddSecondWithLastSec(
			@Param("id") String id,
		    @Param("studyTimeLength") int studyTimeLength,
			@Param("lastLearnLocation") Integer lastLearnLocation
	);

	/**
	 * @Author WangZhen
	 * @Description 根据学员的课程关联id查询未通过学习的章节数
	 * @Date 2020/7/15 10:58
	 **/
    int countPractiseUnPassNodeOfStuCourse(String stuCourseId);

    List<SocietyStudentAndNodeView> selectAAA();

	/**
	 * @Author WangZhen
	 * @Description 根据唯一索引更新学习情况
	 * @Date 2020/8/13 9:27
	 **/
	int updateStudyInfoByOwnerStuCourseIdAndNodeId(SocietyStudentAndNode studentAndNode);


}