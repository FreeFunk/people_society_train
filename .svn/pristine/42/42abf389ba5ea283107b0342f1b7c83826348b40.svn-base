package com.edgedo.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.SysCityAppInfo;
import com.edgedo.sys.queryvo.SysCityAppInfoQuery;
import com.edgedo.sys.queryvo.SysCityAppInfoView;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysCityAppInfoMapper  extends BaseMapper<SysCityAppInfo> {
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysCityAppInfoView> listPage(SysCityAppInfoQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysCityAppInfoView> listByObj(SysCityAppInfoQuery query);
	
	

}