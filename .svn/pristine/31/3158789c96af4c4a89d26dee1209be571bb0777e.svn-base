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
	 * 根据key查询value
	 * @param key
	 * @return
	 */
    String loadValueByKey(String key);

}