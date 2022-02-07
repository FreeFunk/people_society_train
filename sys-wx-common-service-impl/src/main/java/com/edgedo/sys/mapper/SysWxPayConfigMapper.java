package com.edgedo.sys.mapper;

import java.util.List;

import com.edgedo.sys.entity.SysWxPayConfig;
import com.edgedo.sys.queryvo.SysWxPayConfigQuery;
import com.edgedo.sys.queryvo.SysWxPayConfigView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;

@Mapper
public interface SysWxPayConfigMapper  extends BaseMapper<SysWxPayConfig>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysWxPayConfigView> listPage(SysWxPayConfigQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysWxPayConfigView> listByObj(SysWxPayConfigQuery query);
	
	

}