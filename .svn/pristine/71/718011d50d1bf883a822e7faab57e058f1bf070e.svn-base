package com.edgedo.pay.wxpay.comunicate.event.vo;


import com.edgedo.pay.wxpay.comunicate.event.vo.BaseEvent;
import com.edgedo.pay.wxpay.comunicate.msg.MsgSenderFactory;
import com.edgedo.pay.wxpay.comunicate.msg.vo.Article;
import com.edgedo.pay.wxpay.comunicate.msg.vo.NewsMsgSender;
import com.edgedo.pay.wxpay.comunicate.msg.vo.TextMsgSender;

import java.util.ArrayList;
import java.util.List;

/**关注事件
 * @author song
 *
 */
public class SubscribeEvent extends BaseEvent {
	/**
	 * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
	 */
	private String EventKey;
	/**
	 * 二维码的ticket，可用来换取二维码图片
	 */
	private String Ticket;
	
	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}	
	/**
	 * 处理关注事件
	 * @return
	 */
	public String handEvent()throws Exception{
		String message = "";
		MsgSenderFactory msgSenderFactory = MsgSenderFactory.getInstance();
		//关注事件返回消息
		NewsMsgSender newsMsgSender = (NewsMsgSender)msgSenderFactory.creator(MsgSenderFactory.NEWS_MSG_TYPE, getEncryptType(), getTimestamp(), getNonce());
		newsMsgSender.setFromUserName(getToUserName());
		newsMsgSender.setToUserName(getFromUserName());
		//从系统配置处获得应该返回的信息
		String msg = "";
//		String msg = "NEWS_鲜农府用最便宜的价格买到最新鲜的食材@让你恋上做饭，恋上家里的饭菜！@http://xiannongfu.tuyunwangluo.com/m/assets/images/a3.png@http://xiannongfu.tuyunwangluo.com/wx/login/home,鲜农府送樱桃采摘门票一张哦@关还不快快查看@http://xiannongfu.tuyunwangluo.com/m/assets/images/yingtao.png@http://xiannongfu.tuyunwangluo.com/wx/login/myliquan";
		String[] msgTypeArr = msg.split("_");
		if(msgTypeArr.length==2){
			if(msgTypeArr[0].equals("NEWS")){
				List<Article> articles = genArticleList(msgTypeArr[1]);
				newsMsgSender.setArticles(articles);
				message = newsMsgSender.transform();
			}else{
				TextMsgSender textMsgSender = (TextMsgSender)msgSenderFactory.creator(MsgSenderFactory.TEXT_MSG_TYPE, getEncryptType(), getTimestamp(), getNonce());
				textMsgSender.setFromUserName(getToUserName());
				textMsgSender.setToUserName(getFromUserName());
				textMsgSender.setContent(msgTypeArr[1]);
				message = textMsgSender.transform();
			}
		}
		return message;
	}

	@Override
	public String handEvent(String msg) throws Exception {
		String message = "";
		MsgSenderFactory msgSenderFactory = MsgSenderFactory.getInstance();
		//关注事件返回消息
		TextMsgSender textMsgSender = (TextMsgSender)msgSenderFactory.creator(MsgSenderFactory.TEXT_MSG_TYPE, getEncryptType(), getTimestamp(), getNonce());
		textMsgSender.setFromUserName(getToUserName());
		textMsgSender.setToUserName(getFromUserName());
		textMsgSender.setContent(msg);
		message = textMsgSender.transform();
		return message;
	}

	private List<Article> genArticleList(String msg){
		List<Article> articles = new ArrayList<Article>();
//		title1@description1@picurl@url,title1@description1@picurl@url
		String[] arts = msg.split(",");
		for(int i=0;i<arts.length;i++){
			String art = arts[i];
			String[] pros = art.split("@");
			if(pros.length==4){
				String title =  pros[0];
				String description = pros[1];
				String picurl = pros[2];
				String url = pros[3];
				Article article = new Article();
				article.setTitle(title);
				article.setPicUrl(picurl);
				article.setDescription(description);
				article.setUrl(url);
				articles.add(article);
			}
		}
		return articles;
	}

	

}
