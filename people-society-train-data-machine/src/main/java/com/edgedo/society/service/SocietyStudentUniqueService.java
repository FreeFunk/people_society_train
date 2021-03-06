package com.edgedo.society.service;
		
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.constant.ThirdPartyType;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.MD5;
import com.edgedo.common.util.RedisUtil;
import com.edgedo.society.constant.RedisKeyConstant;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentUnique;
import com.edgedo.society.mapper.SocietyStudentUniqueMapper;
import com.edgedo.society.queryvo.SocietyStudentUniqueQuery;
import com.edgedo.society.queryvo.SocietyStudentUniqueView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentUniqueService {


	@Autowired
	RedisUtil redisUtil;
	@Autowired
	private SocietyStudentUniqueMapper societyStudentUniqueMapper;
	@Autowired
	private SocietyStudentService societyStudentService;

	public List<SocietyStudentUniqueView> listPage(SocietyStudentUniqueQuery societyStudentUniqueQuery){
		List list = societyStudentUniqueMapper.listPage(societyStudentUniqueQuery);
		societyStudentUniqueQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentUnique societyStudentUnique) {
		societyStudentUnique.setId(Guid.guid());
		societyStudentUniqueMapper.insert(societyStudentUnique);
		return "";
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertStuAndUnique(SocietyStudentUnique societyStudentUnique) {
		if(societyStudentUnique.getId()==null || societyStudentUnique.getId().equals("")){
			societyStudentUnique.setId(Guid.guid());
		}
		societyStudentUniqueMapper.insert(societyStudentUnique);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentUnique societyStudentUnique) {
		societyStudentUniqueMapper.updateById(societyStudentUnique);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentUnique societyStudentUnique) {
		societyStudentUniqueMapper.updateAllColumnById(societyStudentUnique);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentUniqueMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentUniqueMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentUnique loadById(String id) {
		return societyStudentUniqueMapper.selectById(id);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateById(SocietyStudentUnique societyStudentUnique) {
		societyStudentUniqueMapper.updateById(societyStudentUnique);
	}

	/**
	 * @Author WangZhen
	 * @Description 清理缓存中的用户
	 * @Date 2020/5/20 15:48
	 **/
	public void clearStudentCatch(SocietyStudentUnique student){
		String openId = student.getWxOpenId();
		String accessToken = student.getAccessToken();
		//清空缓存
		String key1 = RedisKeyConstant.selectByOpenIdAndTypeSimple
				+ ThirdPartyType.wechat + "_" + openId;
		String key2 = RedisKeyConstant.selectByOpenIdAndTypeSimple
				+ ThirdPartyType.aboc + "_" + openId;
		String key3 = RedisKeyConstant.selectByAccessTokenAndSimple + accessToken ;
		redisUtil.del(key1,key2,key3);
	}

	public void deleteByStuId(String idCard) {
		societyStudentUniqueMapper.deleteByStuId(idCard);
	}

	public void updateByStuId(Map<String, String> map) {
		societyStudentUniqueMapper.updateByStuId(map);
	}

	public SocietyStudentUnique selectByPhone(String newPhone) {
		return societyStudentUniqueMapper.selectByPhone(newPhone);
	}

	public void updateByAllId(SocietyStudentUnique societyStudentUniqueOld) {
		societyStudentUniqueMapper.updateByAllId(societyStudentUniqueOld);
	}

	//* 根据id重置学员的密码
	public void resetPwdById(String id) {
		SocietyStudent societyStudent = societyStudentService.loadById(id);
		SocietyStudentUnique societyStudentUnique = loadById(societyStudent.getStudentIdCardNum());
		if(societyStudentUnique==null){
			SocietyStudentUnique stuUnique = new SocietyStudentUnique();
			stuUnique.setId(societyStudent.getStudentIdCardNum());
			stuUnique.setIsRealNameAuth(societyStudent.getIsRealNameAuth());
			stuUnique.setFaceImageUrl(societyStudent.getFaceImageUrl());
			stuUnique.setIdCardImage(societyStudent.getIdCardImage());
			stuUnique.setAccessToken(societyStudent.getAccessToken());
			stuUnique.setHeadPhoto(societyStudent.getHeadPhoto());
			stuUnique.setIsUpPwd("0");
			stuUnique.setNickName(societyStudent.getNickName());
			//默认密码123456
			String stuId = stuUnique.getId();
			String encodePwd = MD5.encode(MD5.encode("123456") + stuId);
			stuUnique.setPassword(encodePwd);
			stuUnique.setWxOpenId(societyStudent.getWxOpenId());
			stuUnique.setAbcOpenId(societyStudent.getAbcOpenId());
			stuUnique.setCreateTime(new Date());
			stuUnique.setCreateUserId(societyStudent.getCreateUserId());
			stuUnique.setCreateUserName(societyStudent.getCreateUserName());
			stuUnique.setRealNameOperId(societyStudent.getRealNameOperId());
			stuUnique.setRealNameOperName(societyStudent.getRealNameOperName());
			stuUnique.setRealNameTime(societyStudent.getRealNameTime());
			stuUnique.setRealNameType(societyStudent.getRealNameType());
			stuUnique.setStudentAge(societyStudent.getStudentAge());
			stuUnique.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
			stuUnique.setStudentPhoneNum(societyStudent.getStudentPhoneNum());
			stuUnique.setStudentName(societyStudent.getStudentName());
			stuUnique.setStudentSex(societyStudent.getStudentSex());
			insertStuAndUnique(stuUnique);
			String pwdStu = MD5.encode(MD5.encode("123456")+societyStudent.getId());
			societyStudent.setPassword(pwdStu);
			societyStudentService.updateById(societyStudent);
		}else {
			String password = "123456";
			String pwdUnique = MD5.encode(MD5.encode(password)+societyStudentUnique.getId());
			societyStudentUnique.setPassword(pwdUnique);
			String pwdStu = MD5.encode(MD5.encode(password)+societyStudent.getId());
			societyStudent.setPassword(pwdStu);
			societyStudentUniqueMapper.updateById(societyStudentUnique);
			societyStudentService.updateById(societyStudent);
		}
	}
}
