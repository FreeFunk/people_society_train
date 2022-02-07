package com.edgedo.sys.mapper;

import java.util.List;

import com.edgedo.sys.entity.SysWxTemplateMsgconfig;
import com.edgedo.sys.queryvo.SysWxTemplateMsgconfigQuery;
import com.edgedo.sys.queryvo.SysWxTemplateMsgconfigView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.mapper.BaseMapper;

@Mapper
public interface SysWxTemplateMsgconfigMapper  extends BaseMapper<SysWxTemplateMsgconfig>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysWxTemplateMsgconfigView> listPage(SysWxTemplateMsgconfigQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysWxTemplateMsgconfigView> listByObj(SysWxTemplateMsgconfigQuery query);



}