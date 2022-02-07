package com.edgedo.wx.controller;

import com.edgedo.common.base.BaseController;

import com.edgedo.common.base.annotation.Pass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
/**
 * @author WangZhen
 * @description 从微信中心跳转回来登录
 * @date 2020/3/21
 */
@Controller
@RequestMapping("/")
public class WxLoginController extends BaseController {

    @Pass
    @RequestMapping(value = "/wxLogin")
    public ModelAndView wxLogin(
            String token ,String dwr,
            HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
        Cookie cookie = new Cookie("access-token",token);
        cookie.setPath("/");
        response.addCookie(cookie);
        if(dwr!=null && dwr.length()>0){
            //将dwr中的**_**替换成#
            dwr = dwr.replaceAll("\\*\\*_\\*\\*","#");
            return new ModelAndView("redirect:" + dwr);
        }
        return buildResponse(mav);
    }


}
