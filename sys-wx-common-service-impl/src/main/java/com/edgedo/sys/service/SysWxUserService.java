package com.edgedo.sys.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.SysWxUser;
import com.edgedo.sys.entity.WxAccessTokenInfo;
import com.edgedo.sys.mapper.SysWxUserMapper;
import com.edgedo.sys.queryvo.SysWxUserQuery;
import com.edgedo.sys.queryvo.SysWxUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;


@Service
public class SysWxUserService {
	
	
	@Autowired
	private SysWxUserMapper mapper;

	
	public List<SysWxUserView> listPage(SysWxUserQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	public String insert(SysWxUser voObj) {
		voObj.setId(Guid.guid());
//		String nickName = voObj.getNickName();
		/*if(nickName!=null){
			String encNick = EmojiUtil.emojiConverterUnicodeStr(nickName);
			voObj.setNickName(encNick);
		}*/
		voObj.setNickName(null);
		try {
			mapper.insert(voObj);
		}catch (Exception e){
			voObj.setNickName(null);
			mapper.insert(voObj);
		}
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	public String update(SysWxUser voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	public String updateAll(SysWxUser voObj) {
		mapper.updateAllColumnById(voObj);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	public int delete(String id) {
		
		return mapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public int deleteByIds(List<String> ids) {
		
		return mapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SysWxUser loadById(String id) {
		return mapper.selectById(id);
	}
	/**
	 * 根据openid获取微信用户
	 * @param openId
	 * @return
	 */
	public SysWxUserView selectWxUserByOpenId(String openId) {
    	return mapper.selectWxUserByOpenId(openId);
	}

	/**
	 * 获得请求用户授权的连接
	 * @param appId 微信账号id
	 * @param redirectUrl 会跳地址
	 * @param scope snsapi_base 不授权，snsapi_userinfo 授权
	 * @param stateStr
	 * @return
	 */
	public String getSnsapiUserinfoUrl(String appId, String redirectUrl, String scope, String stateStr) {
		String getOpenIdUrl_snsapi =
				"https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appId +
				"&redirect_uri=" + redirectUrl+
				"&response_type=code" +
				"&scope=" + scope +
				"&state=123#wechat_redirect";
		return getOpenIdUrl_snsapi;
	}


	/**
	 * 新增一个微信用户
	 * @param tokenInfo
	 * @return
	 */
	public SysWxUserView updateRegWxUser(WxAccessTokenInfo tokenInfo) {
		SysWxUserView sysWxUserView = new SysWxUserView();
		sysWxUserView.setId(Guid.guid());
		sysWxUserView.setCreateTime(new Date());
		sysWxUserView.setOpenId(tokenInfo.getOpenid());
		insert(sysWxUserView);
		return sysWxUserView;
	}

	/**
	 * 新增一个微信用户
	 * @param tokenInfo
	 * @return
	 */
	public SysWxUserView updateRegWxUser(WxAccessTokenInfo tokenInfo,String appid) {
		SysWxUserView sysWxUserView = new SysWxUserView();
		sysWxUserView.setId(Guid.guid());
		sysWxUserView.setCreateTime(new Date());
		sysWxUserView.setOpenId(tokenInfo.getOpenid());
		sysWxUserView.setAppId(appid);
		insert(sysWxUserView);
		return sysWxUserView;
	}

	public static void loginWxUser(SysWxUserView sysWxUserView){
		HttpSession session= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		session.setAttribute("WX_USER",sysWxUserView);
	}

	public void updateWxUserArea(String provinceId, String cityId) {
		HttpSession session= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		SysWxUserView sysWxUserView = (SysWxUserView) session.getAttribute("WX_USER");
		String appId = sysWxUserView.getAppId();
		if(appId==null || appId.equals("")){
			sysWxUserView.setAppId(cityId);
			mapper.updateWxUserByOpenId(sysWxUserView);
		}
	}

	public void updateWxUserAppIdByOpenId(String openId) {

		mapper.updateWxUserAppIdByOpenId(openId);
	}
}
