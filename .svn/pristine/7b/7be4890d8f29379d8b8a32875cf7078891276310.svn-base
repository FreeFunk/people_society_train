package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentUnique;
import com.edgedo.society.queryvo.SocietyStudentQuery;
import com.edgedo.society.queryvo.SocietyStudentView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentMapper  extends BaseMapper<SocietyStudent>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentView> listPage(SocietyStudentQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentView> listByObj(SocietyStudentQuery query);

	/**
	 * @Author WangZhen
	 * @Description 根据学校id和微信openId 获得学生信息
	 * @Date 2020/5/4 8:54
	 **/
    SocietyStudent selectStuBySchoolIdAndWxOpenId(
    		@Param("schoolId") String schoolId,
			@Param("openId") String openId);

	/**
	 * @Author WangZhen
	 * @Description 根据学校id和农行app openId 获得学生信息
	 * @Date 2020/5/4 8:54
	 **/
	SocietyStudent selectStuBySchoolIdAndAbocOpenId(
			@Param("schoolId") String schoolId,
			@Param("openId") String openId);

	/**
	 * @Author ZhangCC
	 * @Description 根据手机号和学校查询学员
	 * @Date 2020/5/26 16:53
	 **/
	SocietyStudent selectOneByPhoneNumAndSchool(
			@Param("studentPhoneNum") String studentPhoneNum,
			@Param("ownerSchoolId") String ownerSchoolId);

	/**
	 * @Author ZhangCC
	 * @Description 根据身份证号和学校查询学员
	 * @Date 2020/5/26 16:53
	 **/
	SocietyStudent selectOneByIdCardNumAndSchool(Map<String,Object> param);

	/**
	 * @Author ZhangCC
	 * @Description 修改学员实名认证信息
	 * @Date 2020/5/10 10:56
	 **/
	int updateStudentRealName(SocietyStudentUnique student);

	/**
	 * @Author WangZhen
	 * @Description 根据token和学校获得学生信息
	 * @Date 2020/5/11 16:11
	 **/
    SocietyStudentView selectByAccessTokenAndSch(
    		@Param("accessToken") String accessToken,
			@Param("ownerSchoolId") String ownerSchoolId);

    /**
     * @Author WangZhen
     * @Description 查询部分字段， 根据学校id  微信openid查询
     * @Date 2020/5/12 20:01
     **/
    SocietyStudent selectStuBySchoolIdAndWxOpenIdSimple(
    		@Param("schoolId") String schoolId,
			@Param("openId") String openId);
	/**
	 * @Author WangZhen
	 * @Description  查询部分字段， 根据学校id  农行 openid查询
	 * @Date 2020/5/12 20:01
	 **/
	SocietyStudent selectStuBySchoolIdAndAbocOpenIdSimple(
			@Param("schoolId") String schoolId,
		  @Param("openId") String openId);
	/**
	 * @Author WangZhen
	 * @Description 根据token和学校获得学生信息,部分字段
	 * @Date 2020/5/11 16:11
	 **/
	SocietyStudent selectByAccessTokenAndSchSimple(
			@Param("accessToken") String accessToken,
		   @Param("ownerSchoolId") String ownerSchoolId);
    /**
     * @Author ZhangCC
     * @Description 请空非微信用户的accessToken
     * @Date 2020/5/12 20:16
     **/
    void deleteStuAccessToken(String id);

    /**
     * @Author ZhangCC
     * @Description 清空微信用户的openId
     * @Date 2020/5/12 20:16
     **/
    void deleteStuWxOpenId(String id);
	/**
	 * @Author WangZhen
	 * @Description 查询所有
	 * @Date 2020/7/15 20:52
	 **/
    List<SocietyStudentView> listAll();

}