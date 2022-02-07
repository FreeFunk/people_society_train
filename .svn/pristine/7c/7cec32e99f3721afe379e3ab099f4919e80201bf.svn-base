package com.edgedo.pay.wxpay.comunicate.msg.util;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.pay.wxpay.comunicate.msg.vo.TemplateData;
import com.edgedo.pay.wxpay.util.WxCommonUtil;
import com.edgedo.pay.wxpay.util.constant.WeixinHttpUrlConstant;
import com.edgedo.sys.entity.SysWxTemplateMsgconfig;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**发送模板消息工具类例如：支付成功下单成功等，一天最多可调用100000次
 * @author song
 *
 */
public class SendTemplateMessageUtil extends WxCommonUtil {

	/**
	 * 会员通知
	 * first(标题),keyword1(摘要),keyword2(时间),remark(备注)
	 */
	public static final String YWUSER_REMIND_TEMPLATE = "YWUSER_REMIND_TEMPLATE";

	/**
	 * 发送模板消息（参数为用户id，消息类型，数据项【需要按照消息类型里定义的数据参数依次传入】）
	 * @param userId 用户id
	 * @param msgType 消息类型
	 * @param dataList 数据项 参考消息类型
	 * @return
	 */
	/*public static String sendTemplateMessageByType(String userId,String msgType,List<String> dataList,String url){
		//根据用户id取得appid
		String touser = "";
		if(userId !=null && !"".equals(userId) ){
			touser = getWxUserByUserId(userId).getOpenid();
		}
		String result = "";
		TemplateData td = TemplateData.createTemplate(msgType,touser,dataList,url);
		if(td == null){
			return "未定义的消息类型";
		}
		td.setUserId(userId);
		if("".equals(touser)){
			//未绑定微信的需要在客户端显示
		}else{
			//发送模板消息
			result = sendTemplateMessage(td.getOrgId(), td.makeTemplateMSG());
		}
		return result;
	}*/

	/**
	 * 发送模板消息（参数为用户id，消息类型，数据项【需要按照消息类型里定义的数据参数依次传入】）
	 * @param touser 用户id
	 * @param msgType 消息类型
	 * @return
	 */
	/*public static String sendTemplateMessageByType(String touser, String msgType, Map<String,String> dataParam, String url){
		SysWxTemplateMsgconfigService templateMsgconfigService = IocUtil.getBean(SysWxTemplateMsgconfigService.class);
		SysWxTemplateMsgconfig templateMsgconfig = templateMsgconfigService.loadById(msgType);
		String dataStr = genModelParams(dataParam,templateMsgconfig);
		//根据用户id取得appid
		List<String> dataList = new ArrayList<String>();
		String[] arr = dataStr.split(",");
		for(int i=0;i<arr.length;i++){
			dataList.add(arr[i]);
		}
		String result = "";
		TemplateData td = TemplateData.createTemplate(templateMsgconfig,touser,dataList,url);
		if(td == null){
			return "未定义的消息类型";
		}
		if("".equals(touser)){
			//未绑定微信的需要在客户端显示
		}else{
			//发送模板消息
			result = sendTemplateMessage(td.getOrgId(), td.makeTemplateMSG());
		}
		return result;
	}*/
	/*
	* 根据模板消息发动微信提醒
	* */
	public static String sendTemplateMessageByModelId(
			String touser,String modelId,String dataStr,String url,String orginalId){
		//根据用户id取得appid
		List<String> dataList = new ArrayList<String>();
		String[] arr = dataStr.split(",");
		for(int i=0;i<arr.length;i++){
			dataList.add(arr[i]);
		}
		String result = "";
		TemplateData td = TemplateData.createTemplate( modelId,touser,dataList,url,orginalId);
		if(td == null){
			return "未定义的消息类型";
		}
		if("".equals(touser)){
			//未绑定微信的需要在客户端显示
		}else{
			//发送模板消息
			result = sendTemplateMessage(td.getOrgId(), td.makeTemplateMSG());
		}
		return result;
	}


	//去往小程序学习
	/*public static String sendTemplateMessageByType(
			String touser, String msgType,String dataStr,
			String url,String miniprogramAppid,String miniprogramPagepath
	){
		//根据用户id取得appid
		List<String> dataList = new ArrayList<String>();
		String[] arr = dataStr.split(",");
		for(int i=0;i<arr.length;i++){
			dataList.add(arr[i]);
		}
		String result = "";
		TemplateData td = TemplateData.createTemplate(msgType,touser,dataList,url);
		td.setMiniprogramAppid(miniprogramAppid);
		td.setMiniprogramPagepath(miniprogramPagepath);
		if(td == null){
			return "未定义的消息类型";
		}
		if("".equals(touser)){
			//未绑定微信的需要在客户端显示
		}else{
			//发送模板消息
			result = sendTemplateMessage(td.getOrgId(), td.makeTemplateMSG());
		}
		return result;
	}*/



	/**
	 * 发送模板消息方法
	 * 
	 * @param originalId
	 *            公众账号原始ID
	 * @param jsonMsg
	 *            json格式客服消息
	 * @return true|false
	 */
	public static String sendTemplateMessage(String originalId, String jsonMsg) {
		String result = "";
		try{
			//2heJh6nzEzo6MQxGqfdpYYAfq8x8q3zWnCqSf9BJKotL5gWsHb2T29kNVnjUjE7qJqtDGWr7yCChEEXKdjCFANMY8y0qlyx1h6 吃货
			String accessToken =getAccessTokenByOriginalId(originalId);
			String requestUrl = WeixinHttpUrlConstant.SEND_TEMPLATE_MSG_URL
					.replace("ACCESS_TOKEN", accessToken);
			// 发送客服消息
			System.out.println(jsonMsg);
			JSONObject jsonObject = httpRequestForJson(requestUrl, "POST", jsonMsg);
			if (null != jsonObject) {
				int errorCode = (Integer)jsonObject.get("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				if (0 == errorCode) {
					result = "";
				} else {
					log.error("发送模板消息失败 errCode:{} errormsg:{} ", errorCode,
							errorMsg);
					result = errorMsg;
				}
			}
		}catch(Exception e){
			log.error("发送模板消息失败 errormsg:{} ", e.toString());
			result =  e.toString();
		}
		return result;
	}




	//拼接模板消息的参数
	public static String genModelParams(Map<String,String> param,SysWxTemplateMsgconfig templateMsgconfig){
		String model = templateMsgconfig.getTemplateRemark();
		for(Map.Entry<String,String> entry : param.entrySet()){
			String temKey = entry.getKey();
			model = model.replaceAll("#"+temKey+"#",entry.getValue());
		}
		return model;
	}

}
