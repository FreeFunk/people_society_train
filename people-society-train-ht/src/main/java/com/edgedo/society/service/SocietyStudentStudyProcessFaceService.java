package com.edgedo.society.service;
		
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyStudentStudyProcessFace;
import com.edgedo.society.mapper.SocietyStudentStudyProcessFaceMapper;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessFaceQuery;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessFaceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentStudyProcessFaceService {
	
	
	@Autowired
	private SocietyStudentStudyProcessFaceMapper societyStudentStudyProcessFaceMapper;

	public List<SocietyStudentStudyProcessFaceView> listPage(SocietyStudentStudyProcessFaceQuery societyStudentStudyProcessFaceQuery){
		List list = societyStudentStudyProcessFaceMapper.listPage(societyStudentStudyProcessFaceQuery);
		societyStudentStudyProcessFaceQuery.setList(list);
		return list;
	}

	public List<SocietyStudentStudyProcessFaceView> listpageHuiFu(SocietyStudentStudyProcessFaceQuery societyStudentStudyProcessFaceQuery){
		List list = societyStudentStudyProcessFaceMapper.listpageHuiFu(societyStudentStudyProcessFaceQuery);
		societyStudentStudyProcessFaceQuery.setList(list);
		return list;
	}
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentStudyProcessFace societyStudentStudyProcessFace) {
		societyStudentStudyProcessFace.setId(Guid.guid());
		societyStudentStudyProcessFaceMapper.insert(societyStudentStudyProcessFace);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentStudyProcessFace societyStudentStudyProcessFace) {
		societyStudentStudyProcessFaceMapper.updateById(societyStudentStudyProcessFace);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentStudyProcessFace societyStudentStudyProcessFace) {
		societyStudentStudyProcessFaceMapper.updateAllColumnById(societyStudentStudyProcessFace);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentStudyProcessFaceMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids, User user) {
		
		return societyStudentStudyProcessFaceMapper.updateByIdLogin(ids,user,new Date());
	}

	/**
	 * 找回人脸
	 * @param ids
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int retrieveFaceByIds(List<String> ids) {

		return societyStudentStudyProcessFaceMapper.retrieveFaceByIds(ids);
	}
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentStudyProcessFace loadById(String id) {
		return societyStudentStudyProcessFaceMapper.selectById(id);
	}


	public void updateByNodeIdAndNodeName(String nodeId, String nodeName) {
		societyStudentStudyProcessFaceMapper.updateByNodeIdAndNodeName(nodeId,nodeName);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseIdAndCourseName(Map<String, String> map) {
		societyStudentStudyProcessFaceMapper.updateByCourseIdAndCourseName(map);
	}

	public List<SocietyStudentStudyProcessFace> selectByStuCouId(String stuCouId) {
		return societyStudentStudyProcessFaceMapper.selectByStuCouId(stuCouId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByListId(List<SocietyStudentStudyProcessFace> faceList) {
		societyStudentStudyProcessFaceMapper.updateByListId(faceList);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByListId2(List<SocietyStudentStudyProcessFaceView> faceList) {
		societyStudentStudyProcessFaceMapper.updateByListId2(faceList);
	}

	public List<SocietyStudentStudyProcessFace> selectByStuCouIdAndNodeId(String stuCourseId, String nodeId) {
		return societyStudentStudyProcessFaceMapper.selectByStuCouIdAndNodeId(stuCourseId,nodeId);
	}

	public List<SocietyStudentStudyProcessFace> selectByStuCouIdRand(String stuAndCouId) {
		return societyStudentStudyProcessFaceMapper.selectByStuCouIdRand(stuAndCouId);
	}

	public List<SocietyStudentStudyProcessFaceView> selectBySchoolIdAndNodeId(String schoolId, String oldNodeId) {
		return societyStudentStudyProcessFaceMapper.selectBySchoolIdAndNodeId(schoolId,oldNodeId);
	}
}
