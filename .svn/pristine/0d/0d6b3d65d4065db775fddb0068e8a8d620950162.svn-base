package com.edgedo.pay.wxpay.comunicate.event.vo;

import com.edgedo.pay.wxpay.comunicate.event.vo.BaseEvent;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;


/**上传地理位置
 * @author song
 *
 */
public class LocationEvent extends BaseEvent {
	/**
	 * 地理位置纬度
	 */
	private String Latitude;
	/**
	 * 地理位置经度
	 */
	private String Longitude;
	/**
	 * 地理位置精度
	 */
	private String Precision;
	
	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}

	@Override
	public String handEvent() throws Exception {
		/*String openId = getFromUserName();
		String latitude = getLatitude();
		String longitude = getLongitude();
		//吃货的微信信息如果用户没有登录则存数据库，如果已经登录则存session
		Map<String,String> map = CoordinateUtil.getAreaByCoordinate(Longitude, Latitude);
		if(map.keySet().size() > 0){
			User user = User.getCurrentUser();
			String country = "中国";
			String province = map.get("province");
			String city = map.get("city");
			String district = map.get("district");
			String formattedAddress = map.get("formatted_address");
			if(user == null){
				//如果没有登录则去更新吃货微信账户
				WxUser wxUser = new WxUser();
				wxUser.setId(openId);
				wxUser.setCountry(country);
				wxUser.setProvince(province);
				wxUser.setCity(city);
				wxUser.setDistrict(district);
				wxUser.setLocationX(latitude);
				wxUser.setLocationY(longitude);
				wxUser.setLable(formattedAddress);
				
				String[] provincePinyinArr = Chinese2Spell.CN2FirstSpell(province);
				wxUser.setProvincePinyin(provincePinyinArr[2]);
				String[] cityArr = Chinese2Spell.CN2FirstSpell(city);
				wxUser.setCityPinyin(cityArr[2]);
				//更新微信用户信息
				WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
				WxChUserDAOImpl wxChUserDao = (WxChUserDAOImpl)ctx.getBean("wxChUserDao");
				wxChUserDao.updateByPrimaryKeySelective(wxUser);
			}else{
				//如果已经登录则需要更新session
				Map session = ActionContext.getContext().getSession();
				WxUser wxUser = (WxUser)session.get(User.CUR_USER_WEIXIN_SESSION_KEY);
				if(wxUser == null){
					WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
					WxChUserDAOImpl wxChUserDao = (WxChUserDAOImpl)ctx.getBean("wxChUserDao");
					wxUser = wxChUserDao.selectByPrimaryKey(openId);
					session.put(User.CUR_USER_WEIXIN_SESSION_KEY,wxUser);
				}
				wxUser.setCountry(country);
				wxUser.setProvince(province);
				wxUser.setCity(city);
				wxUser.setLocationX(latitude);
				wxUser.setLocationY(longitude);
				wxUser.setLable(formattedAddress);
			}
		}*/
		return "";
	}

	@Override
	public String handEvent(String msg) throws Exception {
		return null;
	}


}
