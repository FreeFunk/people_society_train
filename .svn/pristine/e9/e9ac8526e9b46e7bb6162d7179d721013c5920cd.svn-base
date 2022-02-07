package com.edgedo.society.service;
		
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentCertificate;
import com.edgedo.society.mapper.SocietyStudentCertificateMapper;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import com.edgedo.society.queryvo.SocietyStudentCertificateQuery;
import com.edgedo.society.queryvo.SocietyStudentCertificateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentCertificateService {
	
	
	@Autowired
	private SocietyStudentCertificateMapper societyStudentCertificateMapper;
	@Autowired
	private SocietyStudentAndCourseService studentAndCourseService;
	@Autowired
	private SocietySchoolCourseService courseService;
	@Autowired
	private SocietyStudentService studentService;
	@Autowired
	private SocietySchoolClassService classService;

	public List<SocietyStudentCertificateView> listPage(SocietyStudentCertificateQuery societyStudentCertificateQuery){
		List list = societyStudentCertificateMapper.listPage(societyStudentCertificateQuery);
		societyStudentCertificateQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentCertificate societyStudentCertificate) {
		societyStudentCertificate.setId(Guid.guid());
		societyStudentCertificate.setCreateTime(new Date());
		societyStudentCertificate.setDataState("1");
		societyStudentCertificateMapper.insert(societyStudentCertificate);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentCertificate societyStudentCertificate) {
		societyStudentCertificateMapper.updateById(societyStudentCertificate);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentCertificate societyStudentCertificate) {
		societyStudentCertificateMapper.updateAllColumnById(societyStudentCertificate);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentCertificateMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentCertificateMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentCertificate loadById(String id) {
		return societyStudentCertificateMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学员查询证书数量
	 * @Date 2020/5/7 15:49
	 **/
	public int countStudentCert(String studentId){
		return societyStudentCertificateMapper.countStudentCert(studentId);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学员和课程查询证书
	 * @Date 2020/5/19 10:12
	 **/
	public SocietyStudentCertificate selectCertByStuAndCourse(String studentId,String courseId){
		Map<String,Object> param = new HashMap<>();
		param.put("studentId",studentId);
		param.put("courseId",courseId);
		return societyStudentCertificateMapper.selectCertByStuAndCourse(param);
	}

	/**
	 * @Author ZhangCC
	 * @Description 学员插入证书信息
	 * @Date 2020/5/19 8:59
	 **/
	public String insertStudentCert(String courseId,SocietyStudent student){
		String result = "";
		SocietyStudentCertificate stuCert = selectCertByStuAndCourse(student.getId(),courseId);
		if(stuCert == null){
			//合格的情况下颁发证书(没有证书的情况下)
			SocietyStudentAndCourseView stuCourse = studentAndCourseService.selectOneFinishedStuCourse(courseId,student.getId());
			if(stuCourse == null){
				result = "未查询到学员课程信息！";
				return result;
			}
			SocietySchoolCourse course = courseService.loadById(courseId);
			if(course == null){
				result =  "未查询到课程信息！";
				return result;
			}

			SocietyStudentCertificate studentCert = new SocietyStudentCertificate();
			studentCert.setOwnerSchoolId(student.getOwnerSchoolId());
			studentCert.setOwnerSchoolName(student.getOwnerSchoolName());
			studentCert.setOwnerMajorId(course.getOwnerMajorId());
			studentCert.setOwnerMajorName(course.getOwnerMajorName());
			String classId = stuCourse.getClassId();
			SocietySchoolClass schoolClass = classService.loadById(classId);
			if(schoolClass != null){
				studentCert.setClassId(classId);
				studentCert.setClassName(schoolClass.getClassName());
			}
			studentCert.setCourseId(course.getId());
			studentCert.setCourseName(course.getCourseName());
			studentCert.setStudentId(student.getId());
			studentCert.setStudentName(student.getStudentName());
			studentCert.setStudentIdCardNum(student.getStudentIdCardNum());
			studentCert.setClassAndStudentId(stuCourse.getId());
			studentCert.setCertificateCode(Guid.guid());
			studentCert.setCertificateName(course.getCourseName()+"课程合格证书");
			studentCert.setCertificateTime(new Date());
			studentCert.setCertificateImageUrl(course.getCourseImage());
			insert(studentCert);
		}

		return result;
	}

	/**
	 * @Author WangZhen
	 * @Description 根据身份证号统计整数数量
	 * @Date 2020/7/14 9:07
	 **/
    public int countStudentCertByStudenIdCard(String stuIdCardNum) {
		return societyStudentCertificateMapper.countStudentCertByStudenIdCard(stuIdCardNum);
    }

    /**
     * @Author WangZhen
     * @Description 根据身份证号和课程id获得一个证书
     * @Date 2020/7/14 10:06
     **/
	public SocietyStudentCertificate selectCertByStuIdCardAndCourse(String stuIdCardNum, String courseId) {
		return societyStudentCertificateMapper.selectCertByStuIdCardAndCourse(stuIdCardNum,courseId);
	}

}
