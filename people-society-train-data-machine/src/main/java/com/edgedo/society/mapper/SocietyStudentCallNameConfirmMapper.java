package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentCallNameConfirm;
import com.edgedo.society.queryvo.SocietyStudentCallNameConfirmQuery;
import com.edgedo.society.queryvo.SocietyStudentCallNameConfirmView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyStudentCallNameConfirmMapper  extends BaseMapper<SocietyStudentCallNameConfirm>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCallNameConfirmView> listPage(SocietyStudentCallNameConfirmQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCallNameConfirmView> listByObj(SocietyStudentCallNameConfirmQuery query);
	
	

}