package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolClassCallName;
import com.edgedo.society.queryvo.SocietySchoolClassCallNameQuery;
import com.edgedo.society.queryvo.SocietySchoolClassCallNameView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolClassCallNameMapper  extends BaseMapper<SocietySchoolClassCallName>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassCallNameView> listPage(SocietySchoolClassCallNameQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassCallNameView> listByObj(SocietySchoolClassCallNameQuery query);
	
	

}