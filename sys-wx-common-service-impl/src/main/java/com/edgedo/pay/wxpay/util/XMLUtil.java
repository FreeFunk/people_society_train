package com.edgedo.pay.wxpay.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

/**处理xml工具类
 * @author song
 *
 */
public class XMLUtil extends WxCommonUtil {
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws DocumentException
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map<String,String> doXMLParse(String strxml) throws DocumentException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map<String,String> m = new HashMap<String,String>();
		
		// 将字符串转化为XML文档对象
		Document document = DocumentHelper.parseText(strxml);
		// 获得文档的根节点
		Element root = document.getRootElement();
		
		List list = root.elements();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.elements();
			if(children == null || children.isEmpty()) {
				v = e.getText();
			} else {
				v = XMLUtil.getChildrenText(children);
			}
			
			m.put(k, v);
		}		
		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getText();
				List list = e.elements();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 解析xml,返回可排序列表
	 * @param strxml
	 * @return
	 * @throws DocumentException
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static SortedMap<String,String> doXMLParseToSortedMap(String strxml) throws DocumentException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		SortedMap<String,String> m = new TreeMap<String,String>();
		
		// 将字符串转化为XML文档对象
		Document document = DocumentHelper.parseText(strxml);
		// 获得文档的根节点
		Element root = document.getRootElement();
		
		List list = root.elements();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.elements();
			if(children == null || children.isEmpty()) {
				v = e.getText();
			} else {
				v = XMLUtil.getChildrenText(children);
			}
			
			m.put(k, v);
		}		
		return m;
	}
	
	public static void main(String[] args){
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("<xml>");
		strBuf.append("<appid>wx2421b1c4370ec43b</appid>");
		strBuf.append("<attach><![CDATA[att1]]></attach>");
		strBuf.append("<body><![CDATA[JSAPI 支付测试]]></body>");
		strBuf.append("<device_info>1000</device_info>");
		strBuf.append("<mch_id>10000100</mch_id>");
		strBuf.append("<nonce_str>b927722419c52622651a871d1d9ed8b2</nonce_str>");
		strBuf.append("<notify_url><![CDATA[http://wxpay.weixin.qq.com/pub_v2/pay/notify.php]]></notify_url>");
		strBuf.append("<out_trade_no>1405713376</out_trade_no>");
		strBuf.append("<spbill_create_ip><![CDATA[127.0.0.1]]></spbill_create_ip>");
		strBuf.append("<total_fee>1</total_fee>");
		strBuf.append("<trade_type>JSAPI</trade_type>");
		strBuf.append("<sign><![CDATA[3CA89B5870F944736C657979192E1CF4]]></sign>");
		strBuf.append("<song>");
		strBuf.append("<song1>12345</song1>");
		strBuf.append("<song2>12345</song2>");
		strBuf.append("<song3>12345</song3>");
		strBuf.append("</song>");
		strBuf.append("</xml>");
		
		try {
			Map<String,String> m = doXMLParse(strBuf.toString());
			System.out.println(m.get("song"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
