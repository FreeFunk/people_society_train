package com.edgedo.society.constant;

/**
 * @author WangZhen
 * @description
 * @date 2020/5/4
 */
public class RedisKeyConstant {

    //根据openid和客户端类型和学校存储学生
    public static String selectByOpenIdAndTypeSchoolSimple = "stuOIdTypeSchSim_";
    //根据token和学校id存储学生
    public static String selectByAccessTokenAndSchSimple = "stuTokenSchSim_";

    //根据token和学校id存储学生
    public static String selectByAccessTokenAndSimple = "stuTokenSim_";
    //根据openid和客户端类型存储全局学生
    public static String selectByOpenIdAndTypeSimple = "stuOIdTypeSim_";

}
