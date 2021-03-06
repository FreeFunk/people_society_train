package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentUnique;
import com.edgedo.society.queryvo.SocietyStudentUniqueQuery;
import com.edgedo.society.queryvo.SocietyStudentUniqueView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentUniqueMapper  extends BaseMapper<SocietyStudentUnique>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentUniqueView> listPage(SocietyStudentUniqueQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentUniqueView> listByObj(SocietyStudentUniqueQuery query);

	/**
	 * @Author WangZhen
	 * @Description 根据手机号查询
	 * @Date 2020/7/13 15:54
	 **/
    SocietyStudentUnique selectOneStuByPhoneNum(String studentPhoneNum);

    /**
     * @Author WangZhen
     * @Description 根据微信openId获得学生信息
     * @Date 2020/7/13 16:01
     **/
	SocietyStudentUnique selectStuByWxOpenId(String openId);
	/**
	 * @Author WangZhen
	 * @Description 根据微信openId获得学生信息
	 * @Date 2020/7/13 16:01
	 **/
	SocietyStudentUnique selectStuByAbocOpenId(String openId);
	/**
	 * @Author WangZhen
	 * @Description 清空学员的openid
	 * @Date 2020/7/13 16:06
	 **/
	int deleteStuWxOpenId(String id);
	/**
	 * @Author WangZhen
	 * @Description 根据微信openId获得学生简单信息
	 * @Date 2020/7/13 16:01
	 **/
    SocietyStudentUnique selectStuByWxOpenIdSimple(
			String openId);
	/**
	 * @Author WangZhen
	 * @Description 根据农行openId获得学生简单信息
	 * @Date 2020/7/13 16:01
	 **/
	SocietyStudentUnique selectStuByAbocOpenIdSimple(
			String openId);
	/**
	 * @Author WangZhen
	 * @Description  根据accessToken查询简单信息
	 * @Date 2020/7/13 17:00
	 **/
	SocietyStudentUnique selectByAccessTokenAndSchSimple(String accessToken);
	/**
	 * @Author WangZhen
	 * @Description  根据accessToken查询全部信息
	 * @Date 2020/7/13 17:00
	 **/
	SocietyStudentUnique selectByAccessToken(String accessToken);

	/**
	 * @Author WangZhen
	 * @Description 清空登录token
	 * @Date 2020/7/14 8:54
	 **/
    void deleteStuAccessToken(String accessToken);
	/**
	 * @Author WangZhen
	 * @Description 修改学员的实名认证信息
	 * @Date 2020/7/14 9:38
	 **/
	void updateStudentRealName(SocietyStudentUnique student);

	/**
	 * @Author WangZhen
	 * @Description 根据创建时间获得用户
	 * @Date 2020/9/7 8:48
	 **/
	List<SocietyStudentUnique> selectUserByCreateTime(String createTime);

}