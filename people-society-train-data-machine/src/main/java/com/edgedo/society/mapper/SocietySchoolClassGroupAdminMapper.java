package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolClassGroupAdmin;
import com.edgedo.society.queryvo.SocietySchoolClassGroupAdminQuery;
import com.edgedo.society.queryvo.SocietySchoolClassGroupAdminView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolClassGroupAdminMapper  extends BaseMapper<SocietySchoolClassGroupAdmin>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassGroupAdminView> listPage(SocietySchoolClassGroupAdminQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassGroupAdminView> listByObj(SocietySchoolClassGroupAdminQuery query);


    void updateBatchIds(List<String> ids);

    String selectBySysUserId(String sysUserId);
}