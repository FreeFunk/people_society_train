package com.edgedo.society.service;
		
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyStudentCertificate;
import com.edgedo.society.mapper.SocietyStudentCertificateMapper;
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

	public List<SocietyStudentCertificateView> listPage(SocietyStudentCertificateQuery societyStudentCertificateQuery){
		List list = societyStudentCertificateMapper.listPage(societyStudentCertificateQuery);
		societyStudentCertificateQuery.setList(list);
		return list;
	}

	public List<SocietyStudentCertificateView> listByObj(SocietyStudentCertificateQuery societyStudentCertificateQuery){
		List list = societyStudentCertificateMapper.listByObj(societyStudentCertificateQuery);
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
		
		return societyStudentCertificateMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentCertificate loadById(String id) {
		return societyStudentCertificateMapper.selectById(id);
	}


	public void deleteByStuId(String id) {
		societyStudentCertificateMapper.deleteByStuId(id);
	}

	public void updateByStuId(Map<String, String> map) {
		societyStudentCertificateMapper.updateByStuId(map);
	}

	public void updateByCourseIdAndCourseName(Map<String, String> map) {
		societyStudentCertificateMapper.updateByCourseIdAndCourseName(map);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByMajorId(String majorId, String majorName) {
		societyStudentCertificateMapper.updateByMajorId(majorId,majorName);
	}

	public void deleteByStudentIdAndCourseId(String studentId, String ownerCourseId) {
		societyStudentCertificateMapper.updateByStudentIdAndCourseId(studentId,ownerCourseId);
	}

	public SocietyStudentCertificateView selectByStuId(String studentId) {
		return societyStudentCertificateMapper.selectByStuId(studentId);
	}

	public SocietyStudentCertificateView selectByStuIdAndCourId(String studentId, String courseId) {
		return societyStudentCertificateMapper.selectByStuIdAndCourId(studentId,courseId);
	}
}
