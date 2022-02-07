package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolClassAndStudentMapper;
import com.edgedo.society.mapper.SocietyStudentAndCourseMapper;
import com.edgedo.society.queryvo.SocietySchoolClassAndStudentQuery;
import com.edgedo.society.queryvo.SocietySchoolClassAndStudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassAndStudentService {
	
	
	@Autowired
	private SocietySchoolClassAndStudentMapper societySchoolClassAndStudentMapper;

	public List<SocietySchoolClassAndStudentView> listPage(SocietySchoolClassAndStudentQuery societySchoolClassAndStudentQuery){
		List list = societySchoolClassAndStudentMapper.listPage(societySchoolClassAndStudentQuery);
		societySchoolClassAndStudentQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClassAndStudent societySchoolClassAndStudent) {
		societySchoolClassAndStudent.setId(Guid.guid());
		societySchoolClassAndStudent.setCreateTime(new Date());
		societySchoolClassAndStudent.setDataState("1");
		societySchoolClassAndStudentMapper.insert(societySchoolClassAndStudent);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClassAndStudent societySchoolClassAndStudent) {
		societySchoolClassAndStudentMapper.updateById(societySchoolClassAndStudent);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClassAndStudent societySchoolClassAndStudent) {
		societySchoolClassAndStudentMapper.updateAllColumnById(societySchoolClassAndStudent);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolClassAndStudentMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolClassAndStudentMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolClassAndStudent loadById(String id) {
		return societySchoolClassAndStudentMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据班级ID统计班级的人数
	 * @Date 2020/5/26 14:10
	 **/
	public int countStudentByClassId(String classId){
		return societySchoolClassAndStudentMapper.countStudentByClassId(classId);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学员查询班级信息
	 * @Date 2020/5/9 9:52
	 **/
	public SocietySchoolClassAndStudentView selectClassByStuId(String ownerSchoolId,String studentId){
		Map<String,Object> param = new HashMap<>();
		param.put("ownerSchoolId",ownerSchoolId);
		param.put("studentId",studentId);
		return societySchoolClassAndStudentMapper.selectClassByStuId(param);
	}

	/**
	 * @Author ZhangCC
	 * @Description 插入学员与班级的关联
	 * @Date 2020/5/5 8:24
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertClassAndStudentRelation(SocietyStudent student, SocietySchoolClass schoolClass){
		int count = societySchoolClassAndStudentMapper
				.countByStuAndClassId(student.getId(),schoolClass.getId());
		if(count>0){
			return null;
		}
		SocietySchoolClassAndStudent classAndStudent = new SocietySchoolClassAndStudent();
		classAndStudent.setStudentId(student.getId());
		classAndStudent.setStudentName(student.getStudentName());
		classAndStudent.setStudentIdCardNum(student.getStudentIdCardNum());
		classAndStudent.setOwnerSchoolId(schoolClass.getOwnerSchoolId());
		classAndStudent.setOwnerSchoolName(schoolClass.getOwnerSchoolName());
		classAndStudent.setOwnerMajorId(schoolClass.getOwnerMajorId());
		classAndStudent.setOwnerMajorName(schoolClass.getOwnerMajorName());
		classAndStudent.setClassId(schoolClass.getId());
		classAndStudent.setClassName(schoolClass.getClassName());
		classAndStudent.setStudentLeranProgress(new BigDecimal(0));
		classAndStudent.setTotalNodeNum(schoolClass.getTotalLessons());
		classAndStudent.setFinishedNodeNum(0);
		classAndStudent.setLearnIsFinished("0");
		String errMsg = insert(classAndStudent);
		return errMsg;
	}

}
