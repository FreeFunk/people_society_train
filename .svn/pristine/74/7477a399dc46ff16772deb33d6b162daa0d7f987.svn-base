package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentComment;
import com.edgedo.society.queryvo.SocietyStudentCommentQuery;
import com.edgedo.society.queryvo.SocietyStudentCommentView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyStudentCommentMapper  extends BaseMapper<SocietyStudentComment>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCommentView> listPage(SocietyStudentCommentQuery query);
	/**
	 * 分页条件查询 显示减压信息
	 * @param query
	 * @return
	 */
	List listPageSimple(SocietyStudentCommentQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCommentView> listByObj(SocietyStudentCommentQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 查询课程的评论数量
	 * @Date 2020/5/10 10:56
	 **/
	int countByCourseId(String ownerCourseId);

	/**
	 * @Author ZhangCC
	 * @Description 根据课程查询评价
	 * @Date 2020/5/10 10:56
	 **/
	List<SocietyStudentCommentView> selectCommentByCourseId(String ownerCourseId);


}