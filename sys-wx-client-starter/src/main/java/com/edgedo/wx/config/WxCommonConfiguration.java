package com.edgedo.wx.config;

import com.edgedo.common.shiro.ShiroFilterWrap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangZhen
 * @description 微信相关的配置
 * @date 2020/3/21
 */
@Configuration
@ConditionalOnProperty(
        value = "app.shiro.wxloginfilter",
        havingValue = "true",
        matchIfMissing = true
)
public class WxCommonConfiguration {
    //微信的公共服务url
    @Value("${app.commonWxServiceUrl}")
    private String commonWxServiceUrl;
    //token超时时长
    @Value("${app.commonTokenTimeOutSec}")
    private Integer tokenTimeOutSec;

    @Bean
    public ShiroFilterWrap wxShiroLoginFilterWrap(){
        ShiroFilterWrap wrap = new ShiroFilterWrap(
                new WxLoginFilter(commonWxServiceUrl,tokenTimeOutSec));
        return wrap;
    }

    public static void main(String[] args) {

    }

    public String getCommonWxServiceUrl() {
        return commonWxServiceUrl;
    }

    public void setCommonWxServiceUrl(String commonWxServiceUrl) {
        this.commonWxServiceUrl = commonWxServiceUrl;
    }
}
