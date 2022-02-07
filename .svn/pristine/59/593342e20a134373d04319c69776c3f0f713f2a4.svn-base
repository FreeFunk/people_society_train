package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolCourseNodeMapper  extends BaseMapper<SocietySchoolCourseNode>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseNodeView> listPage(SocietySchoolCourseNodeQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseNodeView> listByObj(SocietySchoolCourseNodeQuery query);

	/**
	 * @Author WangZhen
	 * @Description 根据课程id 查询课程章节 只查出简单信息
	 * @Date 2020/5/4 13:51
	 **/
    List<SocietySchoolCourseNodeView> selectCousrseNodesByCourseIdSimple(String courseId);
	/**
	 * @Author WangZhen
	 * @Description 根据主键查询节点名
	 * @Date 2020/5/4 16:58
	 **/
    String selectCousrseNodeNameById(String id);
}