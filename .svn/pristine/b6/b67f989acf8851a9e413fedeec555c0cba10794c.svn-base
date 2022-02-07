package com.edgedo.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.SysConfig;
import com.edgedo.sys.queryvo.SysConfigQuery;
import com.edgedo.sys.queryvo.SysConfigView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface SysConfigMapper  extends BaseMapper<SysConfig> {
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysConfigView> listPage(SysConfigQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysConfigView> listByObj(SysConfigQuery query);
	
	

}