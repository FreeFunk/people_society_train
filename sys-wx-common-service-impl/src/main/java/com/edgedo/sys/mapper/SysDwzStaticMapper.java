package com.edgedo.sys.mapper;

import java.util.List;

import com.edgedo.sys.entity.SysDwzStatic;
import com.edgedo.sys.queryvo.SysDwzStaticQuery;
import com.edgedo.sys.queryvo.SysDwzStaticView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;

@Mapper
public interface SysDwzStaticMapper  extends BaseMapper<SysDwzStatic>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysDwzStaticView> listPage(SysDwzStaticQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysDwzStaticView> listByObj(SysDwzStaticQuery query);
	
	

}