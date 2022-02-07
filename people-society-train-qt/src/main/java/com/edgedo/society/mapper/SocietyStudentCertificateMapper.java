package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentCertificate;
import com.edgedo.society.queryvo.SocietyStudentCertificateQuery;
import com.edgedo.society.queryvo.SocietyStudentCertificateView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentCertificateMapper  extends BaseMapper<SocietyStudentCertificate>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCertificateView> listPage(SocietyStudentCertificateQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCertificateView> listByObj(SocietyStudentCertificateQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 查询学员的证书数量
	 * @Date 2020/5/10 10:55
	 **/
	int countStudentCert(String studentId);

	/**
	 * @Author ZhangCC
	 * @Description 根据学员和课程查询证书
	 * @Date 2020/5/19 10:12
	 **/
	SocietyStudentCertificate selectCertByStuAndCourse(Map<String,Object> param);

	/**
	 * @Author WangZhen
	 * @Description 根据身份证号获得证书
	 * @Date 2020/7/14 9:09
	 **/
    int countStudentCertByStudenIdCard(String stuIdCardNum);

	/**
	 * @Author WangZhen
	 * @Description 根据身份证号和课程id查询
	 * @Date 2020/7/14 10:07
	 **/
    SocietyStudentCertificate selectCertByStuIdCardAndCourse(
			@Param("studentIdCardNum") String studentIdCardNum,
			@Param("courseId") String courseId);

}