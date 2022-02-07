package com.edgedo.sys.mapper;

import java.util.List;

import com.edgedo.sys.entity.SysWxConfig;
import com.edgedo.sys.queryvo.SysWxConfigQuery;
import com.edgedo.sys.queryvo.SysWxConfigView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;

@Mapper
public interface SysWxConfigMapper  extends BaseMapper<SysWxConfig>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysWxConfigView> listPage(SysWxConfigQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysWxConfigView> listByObj(SysWxConfigQuery query);

	/**
	 * @Author WangZhen
	 * @Description 查询所有的微信配置
	 * @Date 2020/3/20 9:52
	 **/
    List<SysWxConfigView> selectAllConfig();

}