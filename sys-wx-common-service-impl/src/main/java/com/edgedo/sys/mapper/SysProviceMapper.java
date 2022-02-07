package com.edgedo.sys.mapper;

import java.util.List;

import com.edgedo.sys.entity.SysProvice;
import com.edgedo.sys.queryvo.SysProviceQuery;
import com.edgedo.sys.queryvo.SysProviceView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;

@Mapper
public interface SysProviceMapper  extends BaseMapper<SysProvice>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysProviceView> listPage(SysProviceQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysProviceView> listByObj(SysProviceQuery query);
	
	

}