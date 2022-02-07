package com.edgedo.society.mapper;

import java.util.List;
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

	/**
	 * @Author WangZhen
	 * @Description  根据faceid,courseNodeId,stucouseId查询 一条记录
	 * @Date 2020/5/6 17:46
	 **/
    SocietyStudentStudyProcess selectProgressOfStuOfCourseByFace(
			@Param("studentId") String studentId,
			@Param("stuCourseId") String stuCourseId,
			@Param("ownerNodeId") String ownerNodeId,
			@Param("beginFaceId") String beginFaceId);
	/**
	 * @Author WangZhen
	 * @Description 给过程增加一定描述
	 * @Date 2020/5/6 17:55
	 **/
	int updateProcessAddSecond(@Param("id") String id,@Param("second") int second);

}