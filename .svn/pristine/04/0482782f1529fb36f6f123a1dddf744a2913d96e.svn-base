package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.queryvo.SocietySchoolClassQuery;
import com.edgedo.society.queryvo.SocietySchoolClassView;
import com.edgedo.society.queryvo.SocietySchoolView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolClassMapper  extends BaseMapper<SocietySchoolClass>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassView> listPage(SocietySchoolClassQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassView> listByObj(SocietySchoolClassQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 根据学校查询所有的班级信息
	 * @Date 2020/5/26 11:26
	 **/
	List<SocietySchoolClassView> selectAllClassBySchool(String ownerSchoolId);

}