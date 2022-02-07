package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolMajor;
import com.edgedo.society.queryvo.SocietySchoolMajorQuery;
import com.edgedo.society.queryvo.SocietySchoolMajorView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolMajorMapper  extends BaseMapper<SocietySchoolMajor>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolMajorView> listPage(SocietySchoolMajorQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolMajorView> listByObj(SocietySchoolMajorQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 根据学校查询所有的专业
	 * @Date 2020/5/10 10:53
	 **/
	List<SocietySchoolMajorView> listAllBySchool(String ownerSchoolId);

	/**
	 * @Author ZhangCC
	 * @Description 根据学校和序号查询4个专业
	 * @Date 2020/5/10 10:53
	 **/
	List<SocietySchoolMajorView> listBySchoolLimit4(String ownerSchoolId);

}