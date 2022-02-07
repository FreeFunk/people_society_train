package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.common.util.IdCardUtil;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.entity.SocietySchoolTeacher;
import com.edgedo.society.mapper.SocietySchoolMapper;
import com.edgedo.society.mapper.SocietySchoolTeacherMapper;
import com.edgedo.society.queryvo.SocietySchoolTeacherQuery;
import com.edgedo.society.queryvo.SocietySchoolTeacherView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolTeacherService {
	
	
	@Autowired
	private SocietySchoolTeacherMapper societySchoolTeacherMapper;

	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;

	public List<SocietySchoolTeacherView> listPage(SocietySchoolTeacherQuery societySchoolTeacherQuery){
		List list = societySchoolTeacherMapper.listPage(societySchoolTeacherQuery);
		societySchoolTeacherQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolTeacher societySchoolTeacher) {
		societySchoolTeacher.setId(Guid.guid());
		societySchoolTeacherMapper.insert(societySchoolTeacher);
		return "";
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertNewMain(SocietySchoolTeacher societySchoolTeacher) {
		SocietySchool societySchool = societySchoolService.loadById(societySchoolTeacher.getOwnerSchoolId());
		//省市县
		societySchoolTeacher.setProvinceId(societySchool.getProvinceId());
		societySchoolTeacher.setProvinceName(societySchool.getProvinceName());
		societySchoolTeacher.setCityId(societySchool.getCityId());
		societySchoolTeacher.setCityName(societySchool.getCityName());
		societySchoolTeacher.setXianquId(societySchool.getXianquId());
		societySchoolTeacher.setXianquName(societySchool.getXianquName());
		//学校名
		societySchoolTeacher.setOwnerSchoolName(societySchool.getSchoolName());
		boolean isFlag = IdCardUtil.isValidatedAllIdcard(societySchoolTeacher.getTeacherIdCardNum().trim());
		if(isFlag){
			//性别
			societySchoolTeacher.setTeacherSex(IdCardUtil.getGenderByIdCard(societySchoolTeacher.getTeacherIdCardNum().trim()));
			//年龄
			societySchoolTeacher.setTeacherAge(IdCardUtil.getAgeByIdCard(societySchoolTeacher.getTeacherIdCardNum().trim()));
		}
		societySchoolTeacher.setId(Guid.guid());
		societySchoolTeacherMapper.insert(societySchoolTeacher);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolTeacher societySchoolTeacher) {
		societySchoolTeacherMapper.updateById(societySchoolTeacher);
		//课程讲师修改
		societySchoolCourseService.updateByTeacherId(societySchoolTeacher.getId(),societySchoolTeacher.getTeacherName());
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolTeacher societySchoolTeacher) {
		societySchoolTeacherMapper.updateAllColumnById(societySchoolTeacher);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolTeacherMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolTeacherMapper.updateVoId(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolTeacher loadById(String id) {
		return societySchoolTeacherMapper.selectById(id);
	}

	/**
	 * 根据学校id查询所有讲师
	 * @param schoolId
	 * @return
	 */
	public List<SocietySchoolTeacher> listBySchoolId(String schoolId) {
		return societySchoolTeacherMapper.listBySchoolId(schoolId);
	}
}
