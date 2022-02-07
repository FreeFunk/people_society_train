package com.edgedo.sys.mapper;

import java.util.List;

import com.edgedo.sys.entity.SysCity;
import com.edgedo.sys.queryvo.SysCityQuery;
import com.edgedo.sys.queryvo.SysCityView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;

@Mapper
public interface SysCityMapper  extends BaseMapper<SysCity>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysCityView> listPage(SysCityQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysCityView> listByObj(SysCityQuery query);


    List<SysCityView> listByProvinceId(String provinceId);
}