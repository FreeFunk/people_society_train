package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolClassCallName;
import com.edgedo.society.entity.SocietyStudentCallNameConfirm;
import com.edgedo.society.mapper.SocietySchoolClassCallNameMapper;
import com.edgedo.society.mapper.SocietyStudentCallNameConfirmMapper;
import com.edgedo.society.queryvo.SocietyStudentCallNameConfirmQuery;
import com.edgedo.society.queryvo.SocietyStudentCallNameConfirmView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentCallNameConfirmService {
	
	
	@Autowired
	private SocietyStudentCallNameConfirmMapper societyStudentCallNameConfirmMapper;

	@Autowired
	private SocietySchoolClassCallNameService classCallNameService;

	public List<SocietyStudentCallNameConfirmView> listPage(SocietyStudentCallNameConfirmQuery societyStudentCallNameConfirmQuery){
		List list = societyStudentCallNameConfirmMapper.listPage(societyStudentCallNameConfirmQuery);
		societyStudentCallNameConfirmQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentCallNameConfirm societyStudentCallNameConfirm) {
		societyStudentCallNameConfirm.setId(Guid.guid());
		societyStudentCallNameConfirmMapper.insert(societyStudentCallNameConfirm);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentCallNameConfirm societyStudentCallNameConfirm) {
		societyStudentCallNameConfirmMapper.updateById(societyStudentCallNameConfirm);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentCallNameConfirm societyStudentCallNameConfirm) {
		societyStudentCallNameConfirmMapper.updateAllColumnById(societyStudentCallNameConfirm);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentCallNameConfirmMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentCallNameConfirmMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentCallNameConfirm loadById(String id) {
		return societyStudentCallNameConfirmMapper.selectById(id);
	}

	/*根据学员id查询点名记录*/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietyStudentCallNameConfirm loadByStuId(String studentId) {
		return societyStudentCallNameConfirmMapper.loadByStuId(studentId);
	}

	/*根据id更新确认状态*/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateCallStateById(String id) {
		SocietyStudentCallNameConfirm societyStudentCallNameConfirm= loadById(id);
		String classCallNameId = societyStudentCallNameConfirm.getClassCallNameId();
		societyStudentCallNameConfirmMapper.updateCallStateById(id);
		//更新确认的人数
		int confirmNum = societyStudentCallNameConfirmMapper.countByClassId(classCallNameId);
		SocietySchoolClassCallName classCallName = classCallNameService.loadById(classCallNameId);
		classCallName.setConfirmStudentNum(confirmNum);
		classCallNameService.update(classCallName);
		return "";
	}
}
