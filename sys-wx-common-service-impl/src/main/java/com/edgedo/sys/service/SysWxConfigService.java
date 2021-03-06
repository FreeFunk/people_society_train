package com.edgedo.sys.service;

import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.edgedo.common.base.BusinessException;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.HttpRequestUtil;
import com.edgedo.pay.wxpay.comunicate.msg.MsgReceiverFactory;
import com.edgedo.pay.wxpay.comunicate.msg.vo.Article;
import com.edgedo.pay.wxpay.comunicate.msg.vo.BaseReceiveMsg;
import com.edgedo.pay.wxpay.comunicate.msg.vo.EventMsg;
import com.edgedo.pay.wxpay.comunicate.msg.vo.NewsMsg;
import com.edgedo.pay.wxpay.sdk.WXPayUtil;
import com.edgedo.pay.wxpay.util.SysStringEncryptor;
import com.edgedo.pay.wxpay.util.WeixinUtil;
import com.edgedo.pay.wxpay.util.WxCommonUtil;
import com.edgedo.pay.wxpay.util.WxPayUtil;
import com.edgedo.pay.wxpay.util.vo.AccessTokenVo;
import com.edgedo.pay.wxpay.util.vo.JsapiTicket;
import com.edgedo.sys.entity.*;
import com.edgedo.sys.mapper.SysWxConfigMapper;
import com.edgedo.sys.queryvo.SysWxConfigQuery;
import com.edgedo.sys.queryvo.SysWxConfigView;
import com.edgedo.sys.queryvo.SysWxUserView;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;


@Service
public class SysWxConfigService {
	
	
	@Autowired
	private SysWxConfigMapper mapper;
	@Autowired
	private SysWxPayRecordService sysWxPayRecordService;
	@Autowired
	private SysWxUserService sysWxUserService;
	@Autowired
	private SysCityAppInfoService sysCityAppInfoService;
	@Autowired
	private SysConfigService sysConfigService;


	
	public List<SysWxConfigView> listPage(SysWxConfigQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * ????????????
	 * @return
	 */
	public String insert(SysWxConfig voObj) {
		voObj.setId(Guid.guid());
		mapper.insert(voObj);
		return "";
	}
	
	/***
	 * ??????????????????
	 * @param
	 * @return
	 */
	public String update(SysWxConfig voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * ?????????
	 * @param
	 * @return
	 */
	public String updateAll(SysWxConfig voObj) {
		mapper.updateAllColumnById(voObj);
		return "";
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 */
	public int delete(String id) {
		
		return mapper.deleteById(id);
	}
	
	/**
	 * ????????????
	 * @param ids
	 */
	public int deleteByIds(List<String> ids) {
		
		return mapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 */
	public SysWxConfig loadById(String id) {
		return mapper.selectById(id);
	}



	/**
	 * ????????????token
	 */
	public void updateFreshAccessToken(){
		List<SysWxConfigView> wxConfigList = mapper.listByObj(new SysWxConfigQuery());
		for(final SysWxConfigView config : wxConfigList){
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					StringEncryptor enc = new SysStringEncryptor();
					String appId = enc.decrypt(config.getAppId());
					String appSecret = enc.decrypt(config.getAppSecret());
					System.out.print("??????accesstoken_appid:" + appId);
					try {
						AccessTokenVo accessTokenVo = WeixinUtil.getAccessToken(appId, appSecret);
						System.out.print("??????accesstoken????????????:" + (accessTokenVo==null));
						if(null != accessTokenVo){
							//???json
							String accessToken  = accessTokenVo.getToken();
							JsapiTicket jsapiTicket = WeixinUtil.getJsapiTicket(accessToken);
							String ticket = jsapiTicket.getTicket();
							config.setAccessToken(enc.encrypt(accessToken));
							config.setTicket(enc.encrypt(ticket));
							update(config);
						}else{
							//???????????????????????????????????????
							Thread.sleep(20 * 1000);
							run();
						}
					} catch (Exception e) {
						//????????????????????????
						try {
							//????????????????????????????????????
							Thread.sleep(20 * 1000);
							run();
						} catch (InterruptedException e1) {
							e.printStackTrace();
						}
					}

				}
			});
			t.start();
		}
	}



	public SysWxPayRecord updateWxPayCallBack(Map<String,String> map) throws Exception {
		String payBillId  = (String) map.get("attach");
		SysWxPayRecord record = sysWxPayRecordService.loadById(payBillId);
		String orginalId = record.getOraId();
		//?????????????????????????????????
		SysWxPayConfig wxPayConfig = WxCommonUtil.getSysWxPayConfig(orginalId);
		String payKey = wxPayConfig.getPayKey();

		if(!WXPayUtil.isSignatureValid(map,payKey)){
			//???????????????????????????
			throw new BusinessException( "???????????????????????????!");
		}

		if ("SUCCESS".equalsIgnoreCase(map.get("result_code"))) {
			//???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
			//1.??????????????????ID???????????????????????????????????????

			if(record == null || (record.getPayResult()!=null && record.getPayResult().equals("SUCCESS"))){
				throw new BusinessException( "?????????????????????????????????????????????!");
			}else{
				int payedMoney =  Integer.parseInt((String) map.get("total_fee"));
				double money = payedMoney/100.0;
				double prepayMoney = record.getTotalFee().doubleValue()/100.0;
				if(prepayMoney!=money){
					throw new BusinessException( "??????????????????????????????????????????!");
				}
				//BILLType??????????????????
				record.setPayResult("SUCCESS");
				record.setPayTime(new Date());
				record.setPayMoney(new BigDecimal(payedMoney));
				record.setTransactionId((String) map.get("transaction_id"));
				record.setDeviceInfo((String) map.get("device_info"));
				record.setBankType((String) map.get("bank_type"));
				record.setTimeEnd((String) map.get("time_end"));
				record.setOpenid((String) map.get("openid"));
				record.setNotifyBody(WxCommonUtil.transMapToString(map));
				int count = sysWxPayRecordService.updateSuccess(record);
				return record;  //??????????????????????????????????????????????????????????????????action???

			}

		}else if("SUCCESS".equalsIgnoreCase(map.get("return_code"))) {
			//???????????? ???????????????  ???????????????
			String payResult = record.getPayResult();
			if(record == null|| payResult.equals("SUCCESS")){
				throw new BusinessException("?????????????????????????????????!");
			}
			//??????????????????????????????
			record.setPayResult("SUCCESS");
			record.setPayTime(new Date());
			record.setTransactionId((String) map.get("transaction_id"));
			record.setNotifyBody(WxCommonUtil.transMapToString(map));
			sysWxPayRecordService.updateSuccess(record);
			return record;  //??????????????????????????????????????????????????????????????????action???
		}else{
			throw new BusinessException("????????????????????????????????????!");
		}

	}


	public  String processWechatMag(String originalId,String encryptType,String timestamp,String nonce,String msgSignature,String xml)throws Exception{
		/** ??????xml?????? */
		if("aes".equals(encryptType)){
			//??????????????????????????????xml
			String encrypt = WxCommonUtil.getEncrypt(xml);
			xml = WxCommonUtil.decryptMsg(originalId,timestamp,nonce,msgSignature,encrypt);
		}
		MsgReceiverFactory weixinMsgFactory = MsgReceiverFactory.getInstance();
		BaseReceiveMsg baseReceiveMsg = weixinMsgFactory.creator(xml, encryptType, timestamp, nonce);
		String result = "";
		String msgType = baseReceiveMsg.getMsgType();
		System.out.println("msgType=============="+msgType);
		if(msgType != null && msgType.equals("event")){
			EventMsg eventMsg = (EventMsg)baseReceiveMsg;
			String eventKey = eventMsg.getEventKey();
			if(eventKey != null && eventKey.equals("TEST_01")){
				result = makeUpCarLocationMsg(eventMsg);
				return result;
			}
		}
		String openid = baseReceiveMsg.getFromUserName();
		SysWxUserView sysWxUserView = sysWxUserService.selectWxUserByOpenId(openid);
		String subscribeEventMSG = "";
		//?????????????????????????????????
		if(sysWxUserView!=null){
			String appId = sysWxUserView.getAppId();
			if(appId!=null && !appId.equals("")){
				SysCityAppInfo sysCityAppInfo  =sysCityAppInfoService.loadById(appId);
				subscribeEventMSG = sysCityAppInfo.getSubscribeEventMSG();
			}else {
				SysConfig sysConfig = sysConfigService.loadById("subscribeEventMSG");
				subscribeEventMSG = sysConfig.getValue();
			}
		}else {
			SysConfig sysConfig = sysConfigService.loadById("subscribeEventMSG");
			subscribeEventMSG = sysConfig.getValue();
		}
		if(baseReceiveMsg != null){
			result = baseReceiveMsg.dealMsg(subscribeEventMSG);
		}
		return result;
	}

	/**
	 * ??????????????????????????????
	 * @param eventMsg
	 * @return
	 */
	public String makeUpCarLocationMsg(EventMsg eventMsg){
		String result = "";
		String openid = eventMsg.getFromUserName();
		SysWxUserView sysWxUserView = sysWxUserService.selectWxUserByOpenId(openid);
		String appId = sysWxUserView.getAppId();
		SysCityAppInfo cityAppInfo = sysCityAppInfoService.loadById(appId);
		String token = cityAppInfo.getAccessToken();
		String cityUrlPrefix = cityAppInfo.getCityUrlPrefix();
		String url = cityUrlPrefix+"/transitCarBaseinfoController!selectCarLocationByOpenId.action";
		Map<String,String> param = new HashMap<>();
		param.put("openId",openid);
		List<JSONObject> carList = new ArrayList<>();
		//??????????????????
		try{
			String response = HttpRequestUtil.sendPostRequest(url,param);
			JSONArray jsonArray = JSON.parseArray(response);
			for(int i=0;i<jsonArray.size();i++){
				carList.add((JSONObject)jsonArray.get(i));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		NewsMsg newsMsg = new NewsMsg();
		newsMsg.setToUserName(eventMsg.getToUserName());
		newsMsg.setFromUserName(eventMsg.getFromUserName());
		newsMsg.setCreateTime(new Date().getTime()+"");
		List<Article> articles = new ArrayList<>();
		Article article = new Article();
		article.setTitle("????????????");
		article.setPicUrl("http://www.qhd12328.com/images/title.png");
		article.setUrl("http://wxgzh.qhd12328.com/sys/wx/login.jsn?d=carslocat");
		articles.add(article);
		for(int i=0;i<carList.size();i++){
			article = new Article();
			String carPlateNum = (String)carList.get(i).get("carPlateNum");
			System.out.println("carPlateNum================="+carPlateNum);
			String carFrameNum = (String)carList.get(i).get("carFrameNum");
			System.out.println("carFrameNum================="+carFrameNum);
			String locationInfo = (String)carList.get(i).get("localtionInfo");
			System.out.println("locationInfo================="+locationInfo);
			String onlineState = (String)carList.get(i).get("onlineState");
			System.out.println("onlineState================="+onlineState);
			article.setTitle(carPlateNum+"  "+locationInfo);
			if(onlineState != null){
				//??????
				if(onlineState.equals("0")){
					article.setPicUrl("http://www.qhd12328.com/images/wx/1.png");
					//??????
				}else if(onlineState.equals("1")){
					article.setPicUrl("http://www.qhd12328.com/images/wx/2.png");
				}else{
					//???????????????
					article.setPicUrl("http://www.qhd12328.com/images/wx/3.png");
				}
			}
			String currentTime =  new Date().getTime()+"";
			SortedMap<String,Object> parameters = new TreeMap<String,Object>();
			parameters.put("appId",appId);
			parameters.put("openid",openid);
			parameters.put("d","viewmap");
			parameters.put("carFrameNum",carFrameNum);
			parameters.put("currentTime",currentTime);
			String sign = WxPayUtil.createSign("UTF-8", parameters,token);
			System.out.println("sign==============="+sign);
			System.out.println("token================="+token);
			String articleUrl = cityUrlPrefix + "/wxCallback!wxgzhLogin.action?appId="+appId+"&openid="+openid+"&d=viewmap"+"&currentTime="+currentTime+"&carFrameNum="+carFrameNum+"&sign="+sign;
			article.setUrl(articleUrl);
			articles.add(article);
		}
		newsMsg.setArticles(articles);
		try{
			result = newsMsg.dealMsg("????????????");
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	//???????????????????????????
    public List<SysWxConfigView> selectAllConfig() {
		return mapper.selectAllConfig();
    }

}
