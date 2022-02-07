package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolConfig;
import com.edgedo.society.queryvo.SocietySchoolConfigQuery;
import com.edgedo.society.queryvo.SocietySchoolConfigView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolConfigMapper  extends BaseMapper<SocietySchoolConfig>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolConfigView> listPage(SocietySchoolConfigQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolConfigView> listByObj(SocietySchoolConfigQuery query);

	/*根据学校id和key查询*/
    SocietySchoolConfig selectBySchoolIdAndKey(String schoolId, String configKey);

	/**
	 * 学校管理员修改学校的配置表
	 * @param voObj
	 */
	void updateByIdAndKey(SocietySchoolConfig voObj);

	/**
	 * 超级管理员修改配置，一起连同学校下的相关配置修改
	 * @param societySchoolConfigOld
	 */
	void updateByIdAndSchoolId(SocietySchoolConfig societySchoolConfigOld);

	/**
	 * 根据学校id和配置id逻辑删除
	 * @param schoolId
	 * @param configId
	 */
	void updateByIdAndKeyId(@Param("schoolId") String schoolId, @Param("configId")String configId);
}