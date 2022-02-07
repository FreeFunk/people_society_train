package com.edgedo.pay.wxpay.comunicate.util;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.pay.wxpay.util.WxCommonUtil;
import com.edgedo.pay.wxpay.util.constant.WeixinHttpUrlConstant;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**用户组操作工具
 * @author song
 *
 */
public class GroupUtil extends WxCommonUtil {
	
	/*public static void main(String[] args){
		List<WxGroup> groups = GroupUtil.getGroups("gh_d1e38e894131");
		WxGroup group = null; 
		for(int i=0;i<groups.size();i++){
			group = groups.get(i);
			System.out.println("id=" + group.getId());
			System.out.println("name="+ group.getName());
			System.out.println("remark="+group.getRemark());
			System.out.println("userAmount="+group.getUserAmount());
			System.out.println("===============================================");
		}
		
	}*/


	/**
	 * 查询用户所在分组
	 * 
	 * @param originalId 公众账号原始ID
	 *           
	 * @param openId
	 *            普通用户的标识，对当前公众号唯一
	 * @return groupid
	 */
	public static int getUserGroupId(String originalId, String openId) {
		int groupId = 0;
		String accessToken = getAccessTokenByOriginalId(originalId);
		String requestUrl = WeixinHttpUrlConstant.GET_PERSONGROUPID_URL.replace("ACCESS_TOKEN",
				accessToken);
		// 需要提交的json数据
		String jsonData = "{\"openid\":\"%s\"}";
		// 创建分组
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST",
				String.format(jsonData, openId));
		if (null != jsonObject) {
			if(jsonObject.get("groupid")!=null){
				groupId = (Integer) jsonObject.get("groupid");
			}else{
				groupId = -1;
				int errorCode = (Integer) jsonObject.get("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("查询用户所在分组失败 errcode:{} errmsg:{} ", errorCode,
						errorMsg);
			}
		}
		return groupId;
	}

	/**
	 * 修改分组名
	 * 
	 * @param originalId 公众账号 原始ID
	 *           
	 * @param groupId
	 *            分组id
	 * @param groupName
	 *            修改后的组名
	 * @return true|false
	 */
	public static boolean updateGroup(String originalId, int groupId,
			String groupName) {
		String accessToken = getAccessTokenByOriginalId(originalId);
		boolean result = false;
		String requestUrl = WeixinHttpUrlConstant.UPDATE_GROUPS_URL.replace("ACCESS_TOKEN",
				accessToken);
		// 需要提交的JSON数据
		String jsonData = "{\"group\": {\"id\": %d, \"name\": \"%s\"}}";
		// 修改分组名
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST",
				String.format(jsonData, groupId, groupName));
		if (null != jsonObject) {
			int errorCode = (Integer) jsonObject.get("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("修改分组失败 errcode:{} errmsg:{} ", errorCode, errorMsg);
			}
		}
		return result;
	}

	/**
	 * 移动用户分组
	 * 
	 * @param originalId
	 *            公众账号原始ID
	 * @param openId
	 *            用户唯一标识符
	 * @param groupId
	 *            分组id
	 * @return true|false
	 */
	public static boolean removeMemberGroups(String originalId, String openId,
			int groupId) {
		
		String accessToken = getAccessTokenByOriginalId(originalId);
		boolean result = false;
		String requestUrl = WeixinHttpUrlConstant.REMOVE_MEMBER_URL.replace("ACCESS_TOKEN",
				accessToken);
		// 需要提交json数据
		String jsonData = "{\"openid\":\"%s\",\"to_groupid\":%d}";
		// 移动用户分组
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST",
				String.format(jsonData, openId, groupId));
		if (null != jsonObject) {
			int errorCode = (Integer) jsonObject.get("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("移动用户分组 失败 errcode:{} errmsg:{} ", errorCode,
						errorMsg);
			}
		}
		return result;
	}

/*	public static void main(String[] args){
		String jsonString = "{"+
				"\"groups\": ["+
			"{"+
            "\"id\": 0,"+ 
            "\"name\": \"未分组\","+ 
            "\"count\": 72596"+
        "},"+ 
        "{"+
            "\"id\": 1,"+ 
            "\"name\": \"黑名单\","+ 
            "\"count\": 36"+
        "},"+ 
        "{"+
            "\"id\": 2,"+ 
            "\"name\": \"星标组\","+ 
            "\"count\": 8"+
        "},"+ 
        "{"+
            "\"id\": 104,"+ 
            "\"name\": \"华东媒\","+ 
            "\"count\": 4"+
        "},"+ 
        "{"+
            "\"id\": 106,"+ 
            "\"name\": \"★不测试组★\","+ 
            "\"count\": 1"+
        "}"+
    "]"+
    "}";
		
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray groupArray = jsonObject.getJSONArray("groups");
		Iterator iterator = groupArray.iterator();
		while(iterator.hasNext()){
			JSONObject obj = (JSONObject)iterator.next();
			String id = obj.getString("id");
			String name = obj.getString("name");
			System.out.println(id+name);
		}
	}*/
}
