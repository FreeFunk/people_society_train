package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.queryvo.SocietySchoolQuery;
import com.edgedo.society.queryvo.SocietySchoolView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolMapper  extends BaseMapper<SocietySchool>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolView> listPage(SocietySchoolQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolView> listByObj(SocietySchoolQuery query);

	/**
	 * @Author WangZhen
	 * @Description 根据学校id查询学校对象
	 * @Date 2020/5/9 20:01
	 **/
    SocietySchoolView selectSimpleSchoolById(String id);

}