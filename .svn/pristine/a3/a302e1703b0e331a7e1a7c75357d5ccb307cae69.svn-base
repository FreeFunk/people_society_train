package com.edgedo.pay.wxpay.comunicate.util;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.pay.wxpay.comunicate.vo.QRCode;
import com.edgedo.pay.wxpay.util.WxCommonUtil;
import com.edgedo.pay.wxpay.util.constant.WeixinHttpUrlConstant;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;

/**
 * 二维码工具类
 * 
 * @author songqingtao
 */
public class QRCodeUtil extends WxCommonUtil {
	/**
	 * 通过ticket换取二维码
	 * 
	 * @param ticket
	 *            获取的二维码ticket
	 * @param savePath
	 *            保存路径
	 * @return String
	 */
	public static String getQRCode(String ticket, String savePath) {
		String filePath = null;
		String requestUrl = WeixinHttpUrlConstant.GET_QRCODE_URL.replace("TICKET",
				urlEncodeUTF8(ticket));
		try {
			
			HttpURLConnection conn = (HttpURLConnection) WxCommonUtil.getProxyUrlConn(requestUrl);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 将ticket作为文件名
			filePath = savePath + ticket + ".jpg";
			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
			conn.disconnect();
		} catch (Exception e) {
			filePath = null;
			log.error("根据ticket换取二维码失败:{}", e);
		}
		return filePath;
	}
	
	/**
	 * 创建临时带参数二维码
	 * 
	 * @param originalId 公众账号原始ID
	 * 
	 * @param expireSeconds
	 *            二维码的有效时间，以秒为单位，最大不超过1800秒
	 * @param sceneId
	 *            场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @return qrcode
	 */
	public static QRCode createTemporaryQRCode(String originalId,
											   int expireSeconds, int sceneId) {
		QRCode qrcode = null;
		String accessToken = getAccessTokenByOriginalId(originalId);
		String requestUrl = WeixinHttpUrlConstant.TEMPORARY_QRCODE_URL.replace("ACCESS_TOKEN",
				accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"expire_seconds\": %d,\"action_name\": \"QR_SCENE\","
				+ "\"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建临时带参数二维码
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST",
				String.format(jsonMsg, expireSeconds, sceneId));
		if (null != jsonObject) {
			try {
				qrcode = new QRCode();
				qrcode.setTicket(jsonObject.getString("ticket"));
				qrcode.setExpireSeconds((Integer)jsonObject.get("expire_seconds"));
			} catch (Exception e) {
				qrcode = null;
				int errorCode = (Integer)jsonObject.get("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("创建临时带参数二维码失败 errCode:{} errormsg:{} ", errorCode,
						errorMsg);
			}
		}
		return qrcode;
	}

	/**
	 * 创建永久带参数二维码
	 * 
	 * @param originalId 公众账号原始ID
	 * 
	 * @param sceneId
	 *            场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @return String
	 */
	public static String createPermanentQRCode(String originalId, int sceneId) {
		String ticket = null;
		String accessToken = getAccessTokenByOriginalId(originalId);
		String requestUrl = WeixinHttpUrlConstant.PERMANENT_QRCODE_URL.replace("ACCESS_TOKEN",
				accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"action_name\": \"QR_SCENE\",\"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建临时带参数二维码
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST",
				String.format(jsonMsg, sceneId));
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				log.info("创建永久带参数二维码成功 ticket:{}", ticket);
			} catch (Exception e) {
				int errorCode = (Integer)jsonObject.get("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("创建永久带参数二维码失败 errCode:{} errormsg:{} ", errorCode,
						errorMsg);
			}
		}
		return ticket;
	}

	public static void main(String[] args) {
		// 获取接口访问凭证
		String originalId = "";
		// 创建临时二维码
		// gQHN7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0pFTUxKVDdsS1Q1UWFOeGtvbTJ3AAIEIn4ZUwMECAcAAA==
		QRCode Temporaryqrcode = createTemporaryQRCode(originalId, 1800, 1);
		System.err.println(Temporaryqrcode.getTicket());
		// 创建永久二维码
		// gQGx8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL1JrTlIyajNsZ2o3NzlyNXFfRzJ3AAIEGKUZUwMEPAAAAA==
		String Permanentqrcode = createPermanentQRCode(originalId, 2);
		System.err.println(Permanentqrcode);
		/*String ticket = "gQHU7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzZreHg4OTNsUk53OVdfdUE2V0tyAAIEN3QEVAMECAcAAA==";
		String savePath = "D:/db";
		// 根据ticket换取二维码
		getQRCode(ticket, savePath);*/
	}

}
