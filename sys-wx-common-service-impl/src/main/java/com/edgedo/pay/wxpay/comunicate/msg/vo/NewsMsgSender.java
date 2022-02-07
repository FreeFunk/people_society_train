package com.edgedo.pay.wxpay.comunicate.msg.vo;

import com.edgedo.pay.wxpay.util.WxCommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 回复图文消息
 * @author song
 *
 */
public class NewsMsgSender extends BaseMsgSender {

	/**
	 * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	 */
	private List<Article> Articles = new ArrayList<Article>();
	
	public int getArticleCount() {
		return Articles.size();
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

	@Override
	public String transform() throws Exception {
		StringBuffer sb = new StringBuffer();
		int articleCount = this.getArticleCount();
    	sb.append("<xml><ToUserName><![CDATA[");
    	sb.append(this.getToUserName());
    	sb.append("]]></ToUserName><FromUserName><![CDATA[");
    	sb.append(this.getFromUserName());
    	sb.append("]]></FromUserName><CreateTime>");
    	sb.append(getCreateTime());
    	sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>");
    	sb.append(articleCount);
    	sb.append("</ArticleCount>");
    	sb.append("<Articles>");
    	Article article = null;
    	for(int i=0;i<articleCount&&i<10;i++){
    		article = Articles.get(i);
    		sb.append("<item><Title><![CDATA[");
        	sb.append(article.getTitle());
        	sb.append("]]></Title><Description><![CDATA[");
        	sb.append(article.getDescription());
        	sb.append("]]></Description><PicUrl><![CDATA[");
        	sb.append(article.getPicUrl());
        	sb.append("]]></PicUrl><Url><![CDATA[");
        	sb.append(article.getUrl());
        	sb.append("]]></Url></item>");
    	}
    	sb.append("</Articles></xml>");
    	String result = sb.toString();
    	if("aes".equals(this.getEncryptType())){
          //加密传输或者是兼容模式传输 往回传的时候也得加密后传
          result = WxCommonUtil.encryptMsg(this.getFromUserName(), result, this.getTimestamp(), this.getNonce());
        }
    	return result;  
	}
}
