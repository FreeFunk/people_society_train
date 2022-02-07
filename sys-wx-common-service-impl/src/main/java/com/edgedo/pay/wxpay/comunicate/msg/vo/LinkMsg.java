package com.edgedo.pay.wxpay.comunicate.msg.vo;

import com.edgedo.pay.wxpay.comunicate.msg.vo.BaseReceiveMsg;

/**链接消息
 * @author song
 *
 */
public class LinkMsg extends BaseReceiveMsg {
	/**
     * 消息标题
     */
    private String Title="";  
    /**
     * 消息描述
     */
    private String Description="";  
    /**
     * 消息链接
     */
    private String Url="";  
    
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	@Override
	public String dealMsg() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}

	public String dealMsg(String msg) throws Exception {
		// TODO Auto-generated method stub
		return "";
	}


}
