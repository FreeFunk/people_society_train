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
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCommentView> listByObj(SocietyStudentCommentQuery query);
	
	

}