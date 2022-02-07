package com.edgedo.pay.wxpay.util.constant;

/** 微信公众平台http请求url常量
 * @author song
 *
 */
public class WeixinHttpUrlConstant {	
	/**
	 * 拉取用户信息url
	 */
	public static final String GET_WEIXIN_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 获取关注者列表url
	 */
	public final static String GET_USERLIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	
	/**
	 * 通过OpenID获取查询用户所在分组url
	 */
	public final static String GET_PERSONGROUPID_URL = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取所有分组信息url
	 */
	public final static String GET_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	/**
	 * 创建分组url
	 */
	public final static String CREATE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	/**
	 * 修改分组url
	 */
	public final static String UPDATE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
	/**
	 * 移动用户分组url
	 */
	public final static String REMOVE_MEMBER_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	
	/**
	 * OAuth2.0引导关注者打开 用户同意授权，获取code页面url.<br/>
	 * 1.scope的设置为：snsapi_base（不弹出授权页面，直接跳转，只能获取用户openid）snsapi_userinfo（弹出授权页面)<br/>
	 * 2.redirect_uri：授权后重定向的回调链接地址，请使用urlencode对链接进行处理<br/>
	 * 方法再commonutil的urlEncodeUTF8()<br/>
	 */
	public final static String FANS_GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	
	/**
	 * OAuth2.0通过code换取网页授权access_token
	 */
	public final static String OAUTH2_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	/**
	 * OAuth2.0刷新access_token
	 */
	public final static String REFRESH_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	/**
	 * OAuth2.0拉取用户信息(需scope为 snsapi_userinfo)
	 */
	public final static String OAUTH2_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/**
	 * 生成临时二维码url
	 */
	public final static String TEMPORARY_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	/**
	 * 生成永久二维码url
	 */
	public final static String PERMANENT_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	/**
	 * 换取二维码url
	 */
	public final static String GET_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	
	/**
	 * 开发者可以通过该接口对指定用户设置备注名，该接口暂时开放给微信认证的服务号
	 */
	public final static String SET_USER_REMARK_URL = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	
	/**
	 * 主动发送客服消息url
	 */
	public final static String SEND_CUSTOM_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	/**
	 * 上传图文消息素材 (高级群发接口)
	 */
	public final static String UPLOAD_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
	/**
	 * 根据分组进行群发 (高级群发接口)
	 */
	public final static String SEND_MASS_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	/**
	 * 上传视频(高级群发接口)
	 */
	public final static String UPLOAD_VIDEO_URL = "https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN";
	/**
	 * 根据OpenID列表群发
	 */
	public final static String SEND_MASS_MSG_OPENID_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	/**
	 * 删除群发(高级群发接口)<br/>
	 * 请注意，只有已经发送成功的消息才能删除删除消息只是将消息的图文详情页失效，<br/>
	 * 已经收到的用户，还是能在其本地看到消息卡片。<br/>
	 * 另外，删除群发消息只能删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
	 */
	public final static String DELETE_MASS_MSG_URL = "https://api.weixin.qq.com//cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN";
	
	/**
	 * 发送模板消息接口
	 */
	public final static String SEND_TEMPLATE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
}
