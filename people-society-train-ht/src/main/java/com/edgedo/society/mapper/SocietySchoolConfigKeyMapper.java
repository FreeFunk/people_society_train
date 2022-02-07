package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolConfigKey;
import com.edgedo.society.queryvo.SocietySchoolConfigKeyQuery;
import com.edgedo.society.queryvo.SocietySchoolConfigKeyView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolConfigKeyMapper  extends BaseMapper<SocietySchoolConfigKey>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolConfigKeyView> listPage(SocietySchoolConfigKeyQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolConfigKeyView> listByObj(SocietySchoolConfigKeyQuery query);


	/**
	 * 超级管理员修改配置 根据id修改
	 * @param societySchoolConfigKey
	 */
    void updateAllById(SocietySchoolConfigKey societySchoolConfigKey);

	/**
	 * 超级管理员根据id逻辑删除
	 * @param ids
	 */
	void logicalDeletion(List<String> ids);
}