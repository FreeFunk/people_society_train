package com.edgedo.society.service;
		
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.common.util.MD5;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.entity.SocietySchoolClassAdmin;
import com.edgedo.society.entity.SocietySchoolClassGroupAdmin;
import com.edgedo.society.mapper.SocietySchoolClassGroupAdminMapper;
import com.edgedo.society.mapper.SocietySchoolClassMapper;
import com.edgedo.society.queryvo.SocietySchoolClassGroupAdminQuery;
import com.edgedo.society.queryvo.SocietySchoolClassGroupAdminView;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.entity.SysUserRole;
import com.edgedo.sys.mapper.SysUserMapper;
import com.edgedo.sys.mapper.SysUserRoleMapper;
import com.edgedo.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassGroupAdminService {
	
	
	@Autowired
	private SocietySchoolClassGroupAdminMapper societySchoolClassGroupAdminMapper;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SocietySchoolClassAdminService societySchoolClassAdminService;

	public List<SocietySchoolClassGroupAdminView> listPage(SocietySchoolClassGroupAdminQuery societySchoolClassGroupAdminQuery){
		List<SocietySchoolClassGroupAdminView> list =
				societySchoolClassGroupAdminMapper.listPage(societySchoolClassGroupAdminQuery);
		for(SocietySchoolClassGroupAdminView societySchoolClassGroupAdminView : list){
			List<String> classAdminId = societySchoolClassAdminService.selectByGroupId(societySchoolClassGroupAdminView.getId());
			societySchoolClassGroupAdminView.setClassAdminIdStr(StringUtils.join(classAdminId, ","));
		}
		societySchoolClassGroupAdminQuery.setList(list);
		return list;
	}
	
	/***
	 * ????????????
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClassGroupAdmin societySchoolClassGroupAdmin) {
		societySchoolClassGroupAdmin.setId(Guid.guid());
		societySchoolClassGroupAdminMapper.insert(societySchoolClassGroupAdmin);
		return "";
	}
	
	/***
	 * ??????????????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClassGroupAdminView societySchoolClassGroupAdmin) {
		//???????????????????????????????????????
		List<String> list = new ArrayList<>();
		list.add(societySchoolClassGroupAdmin.getId());
		societySchoolClassAdminService.updateByClassGroupId(list);
		//??????????????????????????????id ?????????
		String[] strClassId = societySchoolClassGroupAdmin.getClassAdminIdStr().split(",");
		List<String> classIdList = Arrays.asList(strClassId);
		societySchoolClassAdminService.updateByGroupId(classIdList,societySchoolClassGroupAdmin.getId());
		societySchoolClassGroupAdminMapper.updateById(societySchoolClassGroupAdmin);
		return "";
	}
	
	/***
	 * ?????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClassGroupAdmin societySchoolClassGroupAdmin) {
		societySchoolClassGroupAdminMapper.updateAllColumnById(societySchoolClassGroupAdmin);
		return "";
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolClassGroupAdminMapper.deleteById(id);
	}
	
	/**
	 * ????????????
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByIds(List<String> ids) {
		//?????????????????????????????????????????????id
		for(String id : ids){
			SocietySchoolClassGroupAdmin societySchoolClassGroupAdmin =
					societySchoolClassGroupAdminMapper.selectById(id);
			SysUser sysUser = new SysUser();
			sysUser.setId(societySchoolClassGroupAdmin.getSysUserId());
			sysUser.setDataState("0");
			sysUserMapper.updateById(sysUser);

		}
		societySchoolClassAdminService.updateByClassGroupId(ids);
		societySchoolClassGroupAdminMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 */
	public SocietySchoolClassGroupAdmin loadById(String id) {
		return societySchoolClassGroupAdminMapper.selectById(id);
	}


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertSysAndGroup(SocietySchoolClassGroupAdminView societySchoolClassGroupAdmin, String userId, String schoolId) {
		//??????????????????
		SysUser sysUser = new SysUser();
		//id
		sysUser.setId(Guid.guid());
		//??????
		String id = sysUserService.selectByUserCode(societySchoolClassGroupAdmin.getClassGroupAdminCode());
		if(id == null){
			sysUser.setUserCode(societySchoolClassGroupAdmin.getClassGroupAdminCode());
			//????????????
			sysUser.setCreateTime(new Date());
			//?????????
			sysUser.setCreateUser(societySchoolClassGroupAdmin.getCreateUserName());
			//?????????
			sysUser.setUserName(societySchoolClassGroupAdmin.getClassGroupAdminName());
			//??????
			sysUser.setPhone(societySchoolClassGroupAdmin.getClassGroupAdminPhone());
			//??????  ??????123456
			String password = "123456";
			String pwd = MD5.encode(MD5.encode(password)+sysUser.getId());
			sysUser.setPassword(pwd);
			//????????????
			sysUser.setDataState("1");
			//?????????
			sysUser.setDefaultRoleId("SCHOOL_CLASS_GROUP_ADMIN");
			sysUser.setDefaultRoleName("?????????????????????");
			sysUser.setUserState("ACTIVE");
			sysUser.setLoginState("LOGIN");
			//??????
			SysUser sysUserSchool = sysUserService.loadById(userId);
			sysUser.setProvinceId(sysUserSchool.getProvinceId());
			sysUser.setProvinceName(sysUserSchool.getProvinceName());
			sysUser.setCityId(sysUserSchool.getCityId());
			sysUser.setCityName(sysUserSchool.getCityName());
			sysUser.setXianquId(sysUserSchool.getXianquId());
			sysUser.setXianquName(sysUserSchool.getXianquName());
			sysUser.setCompId(schoolId);
			sysUserMapper.insert(sysUser);//???????????????
			//????????????????????????
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setRoleId("SCHOOL_CLASS_GROUP_ADMIN");
			sysUserRole.setUserId(sysUser.getId());
			sysUserRoleMapper.insert(sysUserRole);
			societySchoolClassGroupAdmin.setSysUserId(sysUser.getId());
			societySchoolClassGroupAdmin.setId(Guid.guid());
			societySchoolClassGroupAdminMapper.insert(societySchoolClassGroupAdmin);
			//????????????????????????id
			String[] classAdminIdList = societySchoolClassGroupAdmin.getClassAdminIdStr().split(",");
			if (classAdminIdList.length!=0){
				List<String> list = Arrays.asList(classAdminIdList);
				societySchoolClassAdminService.updateByGroupId(list,societySchoolClassGroupAdmin.getId());
			}
			return "";
		}else {
			return "????????????????????????!";
		}
	}

	public String selectBySysUserId(String sysUserId) {
		return societySchoolClassGroupAdminMapper.selectBySysUserId(sysUserId);
	}
}
