package com.edgedo.society.service;
		
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolClassCallName;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentCallNameConfirm;
import com.edgedo.society.mapper.SocietySchoolClassAndStudentMapper;
import com.edgedo.society.mapper.SocietySchoolClassCallNameMapper;
import com.edgedo.society.mapper.SocietyStudentCallNameConfirmMapper;
import com.edgedo.society.mapper.SocietyStudentMapper;
import com.edgedo.society.queryvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassCallNameService {
	
	
	@Autowired
	private SocietySchoolClassCallNameMapper societySchoolClassCallNameMapper;
	@Autowired
	private SocietyStudentCallNameConfirmMapper societyStudentCallNameConfirmMapper;
	@Autowired
	private SocietySchoolClassAndStudentMapper societySchoolClassAndStudentMapper;
    @Autowired
    private SocietyStudentMapper societyStudentMapper;

	public List<SocietySchoolClassCallNameView> listPage(SocietySchoolClassCallNameQuery societySchoolClassCallNameQuery){
		List list = societySchoolClassCallNameMapper.listPage(societySchoolClassCallNameQuery);
		societySchoolClassCallNameQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClassCallName societySchoolClassCallName) {
		societySchoolClassCallName.setId(Guid.guid());
		societySchoolClassCallNameMapper.insert(societySchoolClassCallName);
		return "";
	}

	/**
	 * 一键点名,学校管理员
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertAutoCallName(SocietySchoolClassCallName societySchoolClassCallName) {
		societySchoolClassCallName.setId(Guid.guid());

		//插入学员点名
		SocietyStudentCallNameConfirm con = null;
        List<SocietyStudentView> stus = new ArrayList<>();
		List<String> studentIdList = societySchoolClassAndStudentMapper.selectStudentIdByClass(societySchoolClassCallName.getClassId());
        if(studentIdList.size() > 0){
            SocietyStudentQuery query = new SocietyStudentQuery();
            query.setStudentIdList(studentIdList);
            stus = societyStudentMapper.classStudentListPage(query);
        }
		for(SocietyStudentView stu : stus){
			con = new SocietyStudentCallNameConfirm();
			con.setId(Guid.guid());
			con.setCreateTime(societySchoolClassCallName.getCreateTime());
			con.setCreateUserId(societySchoolClassCallName.getCreateUserId());
			con.setCreateUserName(societySchoolClassCallName.getCreateUserName());
			con.setClassCallNameId(societySchoolClassCallName.getId());
			con.setOwnerSchoolId(societySchoolClassCallName.getOwnerSchoolId());
			con.setOwnerSchoolName(societySchoolClassCallName.getOwnerSchoolName());
			con.setClassId(societySchoolClassCallName.getClassId());
			con.setClassName(societySchoolClassCallName.getClassName());
			con.setStudentId(stu.getId());
			con.setStudentName(stu.getStudentName());
			con.setStudentIdCardNum(stu.getStudentIdCardNum());
			int tl = societySchoolClassCallName.getTimeLength();
			Date dS = societySchoolClassCallName.getCreateTime();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dS);
			cal.add(Calendar.MINUTE, tl);
			Date dE = cal.getTime();
			con.setEndTime(dE);
			con.setCallState("0");
			societyStudentCallNameConfirmMapper.insert(con);
		}
        societySchoolClassCallName.setTotalStudentNum(stus.size());
        societySchoolClassCallNameMapper.insert(societySchoolClassCallName);
		return "";
	}

    /**
     * 手动点名,学校管理员
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public String insertmanualCallName(SocietySchoolClassCallName societySchoolClassCallName,List<String> studentIdList) {
        societySchoolClassCallName.setId(Guid.guid());

        //插入学员点名
        SocietyStudentCallNameConfirm con = null;
        List<SocietyStudentView> stus = new ArrayList<>();
        SocietyStudentQuery query = new SocietyStudentQuery();
        query.setStudentIdList(studentIdList);
        stus = societyStudentMapper.classStudentListPage(query);
        for(SocietyStudentView stu : stus){
            con = new SocietyStudentCallNameConfirm();
            con.setId(Guid.guid());
            con.setCreateTime(societySchoolClassCallName.getCreateTime());
            con.setCreateUserId(societySchoolClassCallName.getCreateUserId());
            con.setCreateUserName(societySchoolClassCallName.getCreateUserName());
            con.setClassCallNameId(societySchoolClassCallName.getId());
            con.setOwnerSchoolId(societySchoolClassCallName.getOwnerSchoolId());
            con.setOwnerSchoolName(societySchoolClassCallName.getOwnerSchoolName());
            con.setClassId(societySchoolClassCallName.getClassId());
            con.setClassName(societySchoolClassCallName.getClassName());
            con.setStudentId(stu.getId());
            con.setStudentName(stu.getStudentName());
            con.setStudentIdCardNum(stu.getStudentIdCardNum());
            int tl = societySchoolClassCallName.getTimeLength();
            Date dS = societySchoolClassCallName.getCreateTime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dS);
            cal.add(Calendar.MINUTE, tl);
            Date dE = cal.getTime();
            con.setEndTime(dE);
            con.setCallState("0");
            societyStudentCallNameConfirmMapper.insert(con);
        }
        societySchoolClassCallName.setTotalStudentNum(stus.size());
        societySchoolClassCallNameMapper.insert(societySchoolClassCallName);
        return "";
    }
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClassCallName societySchoolClassCallName) {
		societySchoolClassCallNameMapper.updateById(societySchoolClassCallName);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClassCallName societySchoolClassCallName) {
		societySchoolClassCallNameMapper.updateAllColumnById(societySchoolClassCallName);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolClassCallNameMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolClassCallNameMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolClassCallName loadById(String id) {
		return societySchoolClassCallNameMapper.selectById(id);
	}
	

}
