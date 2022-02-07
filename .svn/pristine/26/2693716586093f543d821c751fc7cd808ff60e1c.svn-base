package com.edgedo.pay.wxpay.comunicate.msg.vo;


import com.edgedo.pay.wxpay.comunicate.msg.MsgSenderFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 回复图文消息
 * @author song
 *
 */
public class NewsMsg extends BaseReceiveMsg {

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
	public String dealMsg() throws Exception {
		return null;
	}

	@Override
	public String dealMsg(String msg) throws Exception {
		String result = msg;
		MsgSenderFactory msgSenderFactory = MsgSenderFactory.getInstance();
		NewsMsgSender newsMsgSender = (NewsMsgSender) msgSenderFactory.creator(MsgSenderFactory.NEWS_MSG_TYPE, getEncryptType(), getTimestamp(), getNonce());

		newsMsgSender.setFromUserName(getToUserName());
		newsMsgSender.setToUserName(getFromUserName());
		newsMsgSender.setArticles(Articles);
		result = newsMsgSender.transform();
		return result;
	}

}
