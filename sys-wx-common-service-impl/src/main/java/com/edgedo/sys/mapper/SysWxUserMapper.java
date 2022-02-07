package com.edgedo.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.SysWxUser;
import com.edgedo.sys.queryvo.SysWxUserQuery;
import com.edgedo.sys.queryvo.SysWxUserView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface SysWxUserMapper  extends BaseMapper<SysWxUser> {
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysWxUserView> listPage(SysWxUserQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysWxUserView> listByObj(SysWxUserQuery query);

	/**
	 * 根据openid获取微信用户
	 * @param openId
	 * @return
	 */
	SysWxUserView selectWxUserByOpenId(String openId);

    void updateWxUserByOpenId(SysWxUserView sysWxUserView);

    void updateWxUserAppIdByOpenId(String openId);
}