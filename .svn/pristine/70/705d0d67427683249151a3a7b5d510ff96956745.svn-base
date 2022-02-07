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
    SocietySchoolConfig selectBySchoolIdAndKey(@Param("schoolId") String schoolId,
											   @Param("configKey") String configKey);
	/*根据学校id和key查询value值*/
    String loadValueBySchoolIdAndKey(
    		@Param("schoolId") String schId,
			@Param("configKey")String key);

}