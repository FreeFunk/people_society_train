package com.edgedo.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.SysDwzDynamic;
import com.edgedo.sys.queryvo.SysDwzDynamicQuery;
import com.edgedo.sys.queryvo.SysDwzDynamicView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface SysDwzDynamicMapper  extends BaseMapper<SysDwzDynamic> {
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysDwzDynamicView> listPage(SysDwzDynamicQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysDwzDynamicView> listByObj(SysDwzDynamicQuery query);
	
	

}