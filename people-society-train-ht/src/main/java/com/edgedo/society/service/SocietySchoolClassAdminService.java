package com.edgedo.society.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.common.util.MD5;
import com.edgedo.society.entity.FormSelectsObject;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.entity.SocietySchoolClassAdmin;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.mapper.SocietySchoolClassAdminMapper;
import com.edgedo.society.mapper.SocietySchoolClassMapper;
import com.edgedo.society.queryvo.SocietySchoolClassAdminQuery;
import com.edgedo.society.queryvo.SocietySchoolClassAdminView;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.entity.SysUserRole;
import com.edgedo.sys.mapper.SysUserMapper;
import com.edgedo.sys.mapper.SysUserRoleMapper;
import com.edgedo.sys.mapper.SysWxUserMapper;
import com.edgedo.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassAdminService {
	
	
	@Autowired
	private SocietySchoolClassAdminMapper societySchoolClassAdminMapper;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Autowired
	private SocietySchoolClassMapper societySchoolClassMapper;

	public List<SocietySchoolClassAdminView> listPage(SocietySchoolClassAdminQuery societySchoolClassAdminQuery){
		List list = societySchoolClassAdminMapper.listPage(societySchoolClassAdminQuery);
		societySchoolClassAdminQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClassAdmin societySchoolClassAdmin,String userId,String schoolId) {
		//关联上用户表
		SysUser sysUser = new SysUser();
		//id
		sysUser.setId(Guid.guid());
		//账号
		String id = sysUserService.selectByUserCode(societySchoolClassAdmin.getClassAdminCode());
		if(id == null){
			sysUser.setUserCode(societySchoolClassAdmin.getClassAdminCode());
			//创建时间
			sysUser.setCreateTime(new Date());
			//创建人
			sysUser.setCreateUser(societySchoolClassAdmin.getCreateUserName());
			//用户名
			sysUser.setUserName(societySchoolClassAdmin.getClassAdminName());
			//电话
			sysUser.setPhone(societySchoolClassAdmin.getClassAdminPhone());
			//密码  默认123456
			String password = "123456";
			String pwd = MD5.encode(MD5.encode(password)+sysUser.getId());
			sysUser.setPassword(pwd);
			//数据状态
			sysUser.setDataState("1");
			//用角色
			sysUser.setDefaultRoleId("SCHOOL_CLASS_ADMIN");
			sysUser.setDefaultRoleName("学校班主任");
			sysUser.setUserState("ACTIVE");
			sysUser.setLoginState("LOGIN");
			//县区
			SysUser sysUserSchool = sysUserService.loadById(userId);
			sysUser.setProvinceId(sysUserSchool.getProvinceId());
			sysUser.setProvinceName(sysUserSchool.getProvinceName());
			sysUser.setCityId(sysUserSchool.getCityId());
			sysUser.setCityName(sysUserSchool.getCityName());
			sysUser.setXianquId(sysUserSchool.getXianquId());
			sysUser.setXianquName(sysUserSchool.getXianquName());
			sysUser.setCompId(schoolId);
			sysUserMapper.insert(sysUser);//上升数据库
			//添加用户菜单关联
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setRoleId("SCHOOL_CLASS_ADMIN");
			sysUserRole.setUserId(sysUser.getId());
			sysUserRoleMapper.insert(sysUserRole);
			societySchoolClassAdmin.setSysUserId(sysUser.getId());
			societySchoolClassAdmin.setId(Guid.guid());
			societySchoolClassAdminMapper.insert(societySchoolClassAdmin);
			return "";
		}else {
			return "班主任登录账号重复!";
		}
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClassAdmin societySchoolClassAdmin) {
		SysUser sysUser = new SysUser();
		sysUser.setId(societySchoolClassAdmin.getSysUserId());
		sysUser.setUserCode(societySchoolClassAdmin.getClassAdminCode());
		sysUserMapper.updateById(sysUser);
		societySchoolClassAdminMapper.updateById(societySchoolClassAdmin);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClassAdmin societySchoolClassAdmin) {
		societySchoolClassAdminMapper.updateAllColumnById(societySchoolClassAdmin);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolClassAdminMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		for(String id : ids){
			SocietySchoolClassAdmin societySchoolClassAdmin =
					societySchoolClassAdminMapper.selectById(id);
			SysUser sysUser = new SysUser();
			sysUser.setId(societySchoolClassAdmin.getSysUserId());
			sysUser.setDataState("0");
			sysUserMapper.updateById(sysUser);
			List<SocietySchoolClass> list =
					societySchoolClassService.selectByClassAdminIdVoObj(societySchoolClassAdmin.getId());
			for(SocietySchoolClass societySchoolClass : list){
				societySchoolClass.setPersonInCharge("");
				societySchoolClass.setPersonInChargePhoneNum("");
				societySchoolClass.setClassAdminId("");
				societySchoolClassMapper.updateById(societySchoolClass);
			}
		}
		return societySchoolClassAdminMapper.updateByState(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolClassAdmin loadById(String id) {
		return societySchoolClassAdminMapper.selectById(id);
	}


	public void resetPwdById(String id) {
		SocietySchoolClassAdmin societySchoolClassAdmin = loadById(id);
		String password = "123456";
		String pwd = MD5.encode(MD5.encode(password)+societySchoolClassAdmin.getSysUserId());
		SysUser sysUser = new SysUser();
		sysUser.setPassword(pwd);
		sysUser.setId(societySchoolClassAdmin.getSysUserId());
		sysUserMapper.updateById(sysUser);
	}

	//查出当前学校的所有的班主任
	public List<SocietySchoolClassAdmin> listByClassAdmin(String schoolId) {
		return societySchoolClassAdminMapper.listByClassAdmin(schoolId);
	}

	/**
	 * 根据用户id查出班主任id
	 * @param sysUserId
	 * @return
	 */
	public String selectBySysUserId(String sysUserId) {
		return societySchoolClassAdminMapper.selectBySysUserId(sysUserId);
	}

	/**
	 * 根据 组长id 取消所有关联组长的班主任账号
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByClassGroupId(List<String> ids) {
		societySchoolClassAdminMapper.updateByClassGroupId(ids);
	}

	public List<FormSelectsObject> getClassAdminName(String schoolId,String groupId) {
		return societySchoolClassAdminMapper.getClassAdminName(schoolId,groupId);
	}

	/**
	 * 班主任id 集合 更新对应的组长id
	 * @param classAdminIdList
	 * @param groupId
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByGroupId(List<String> classAdminIdList, String groupId) {
		societySchoolClassAdminMapper.updateByGroupId(classAdminIdList,groupId);
	}

	public List<String> selectByGroupId(String groupId) {
		return societySchoolClassAdminMapper.selectByGroupId(groupId);
	}
}
