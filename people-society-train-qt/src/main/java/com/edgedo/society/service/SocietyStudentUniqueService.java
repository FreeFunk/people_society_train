package com.edgedo.society.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BusinessException;
import com.edgedo.common.constant.ThirdPartyType;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.MD5;
import com.edgedo.common.util.RedisUtil;
import com.edgedo.society.constant.RedisKeyConstant;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentUnique;
import com.edgedo.society.mapper.SocietyStudentMapper;
import com.edgedo.society.mapper.SocietyStudentUniqueMapper;
import com.edgedo.society.queryvo.SocietyStudentUniqueQuery;
import com.edgedo.society.queryvo.SocietyStudentUniqueView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentUniqueService {

	@Autowired
	RedisUtil redisUtil;
	@Value("${app.commonTokenTimeOutSec}")
	private Integer commonTokenTimeOutSec;
	
	@Autowired
	private SocietyStudentUniqueMapper societyStudentUniqueMapper;
	@Autowired
	private SocietyStudentMapper studentMapper;

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

	/**
	 * @Author WangZhen
	 * @Description 根据手机号查找全局学员
	 * @Date 2020/7/13 15:53
	 **/
    public SocietyStudentUnique selectOneStuByPhoneNum(String studentPhoneNum) {
		return societyStudentUniqueMapper.selectOneStuByPhoneNum(studentPhoneNum);
    }

    /**
     * @Author WangZhen
     * @Description 根据openid和类型获得
     * @Date 2020/7/13 15:59
     **/
	public SocietyStudentUnique selectByOpenIdAndType(ThirdPartyType thirdPartyType, String openId) {
		if(thirdPartyType.equals(ThirdPartyType.wechat)){
			return societyStudentUniqueMapper.selectStuByWxOpenId(openId);
		}else if(thirdPartyType.equals(ThirdPartyType.aboc)){
			return societyStudentUniqueMapper.selectStuByAbocOpenId(openId);
		}
		return null;
	}

	/**
	 * @Author WangZhen
	 * @Description 清空学生openid
	 * @Date 2020/7/13 16:05
	 **/
	public int deleteStuWxOpenId(String id) {
		return societyStudentUniqueMapper.deleteStuWxOpenId(id);
	}


	/**
	 * @Author WangZhen
	 * @Description 查询部分字段， 根据学校id  用户类型， openid查询
	 * @Date 2020/5/12 20:01
	 **/
	public SocietyStudentUnique selectByOpenIdAndTypeSimple(ThirdPartyType thirdPartyType, String openId) {
		//实名认证或者退出的时候清空
		String key = RedisKeyConstant.selectByOpenIdAndTypeSimple
				+ thirdPartyType + "_" + openId;
		Object obj = redisUtil.get(key,commonTokenTimeOutSec);
		if(obj!=null){
			return (SocietyStudentUnique)obj;
		}
		SocietyStudentUnique stu = null;
		if(thirdPartyType.equals(ThirdPartyType.wechat)){
			stu =  societyStudentUniqueMapper.selectStuByWxOpenIdSimple(openId);
		}else if(thirdPartyType.equals(ThirdPartyType.aboc)){
			stu = societyStudentUniqueMapper.selectStuByAbocOpenIdSimple(openId);
		}
		if(stu!=null){
			redisUtil.set(key,stu,commonTokenTimeOutSec);
		}
		return stu;
	}

	/**
	 * @Author WangZhen
	 * @Description 根据accesstoken和学校id查询学员
	 * @Date 2020/5/20 15:34
	 **/
	public SocietyStudentUnique selectByAccessTokenSimple(String accessToken) {
		//实名认证或者退出的时候清空
		String key = RedisKeyConstant.selectByAccessTokenAndSimple + accessToken ;
		Object obj = redisUtil.get(key,commonTokenTimeOutSec);
		if(obj!=null){
			return (SocietyStudentUnique)obj;
		}
		SocietyStudentUnique stu = societyStudentUniqueMapper.selectByAccessTokenAndSchSimple(accessToken);
		if(stu!=null){
			redisUtil.set(key,stu,commonTokenTimeOutSec);
		}
		return stu;
	}

	/**
	 * @Author WangZhen
	 * @Description 根据accessToken查询全的信息
	 * @Date 2020/7/13 17:05
	 **/
	public SocietyStudentUnique selectByAccessToken(String accessToken) {
		return societyStudentUniqueMapper.selectByAccessToken(accessToken);
	}

	/**
	 * @Author WangZhen
	 * @Description 清空登录的 token
	 * @Date 2020/7/14 8:54
	 **/
	public void deleteStuAccessToken(String id) {
		societyStudentUniqueMapper.deleteStuAccessToken(id);
	}
	/**
	 * @Author WangZhen
	 * @Description 更新实名信息
	 * @Date 2020/7/14 9:36
	 **/
	public void updateStudentRealName(SocietyStudentUnique student) {
		clearStudentCatch(student);
		societyStudentUniqueMapper.updateStudentRealName(student);
		//更新所有系统内的其他学校学员的实名信息
		studentMapper.updateStudentRealName(student);
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

	/**
	 * @Author WangZhen
	 * @Description 插入一个学生信息
	 * @Date 2020/7/14 11:44
	 **/
	public void insertStuUnique(SocietyStudent stu) {
		String idCard = stu.getStudentIdCardNum();
		String stuPhoneNum = stu.getStudentPhoneNum();
		SocietyStudentUnique stuOraUnique = loadById(idCard);
		if(stuOraUnique==null){
			SocietyStudentUnique stuUnique = new SocietyStudentUnique();
			stuUnique.setId(stu.getStudentIdCardNum());
			stuUnique.setIsRealNameAuth(stu.getIsRealNameAuth());
			stuUnique.setFaceImageUrl(stu.getFaceImageUrl());
			stuUnique.setIdCardImage(stu.getIdCardImage());
			stuUnique.setAccessToken(stu.getAccessToken());
			stuUnique.setHeadPhoto(stu.getHeadPhoto());
			stuUnique.setIsUpPwd("1");
			stuUnique.setNickName(stu.getNickName());
			//默认密码123456
			String stuId = stuUnique.getId();
			String encodePwd = MD5.encode(MD5.encode("123456") + stuId);
			stuUnique.setPassword(encodePwd);
			stuUnique.setWxOpenId(stu.getWxOpenId());
			stuUnique.setAbcOpenId(stu.getAbcOpenId());
			stuUnique.setCreateTime(new Date());
			stuUnique.setCreateUserId(stu.getCreateUserId());
			stuUnique.setCreateUserName(stu.getCreateUserName());
			stuUnique.setRealNameOperId(stu.getRealNameOperId());
			stuUnique.setRealNameOperName(stu.getRealNameOperName());
			stuUnique.setRealNameTime(stu.getRealNameTime());
			stuUnique.setRealNameType(stu.getRealNameType());
			stuUnique.setStudentAge(stu.getStudentAge());
			stuUnique.setStudentIdCardNum(stu.getStudentIdCardNum());
			stuUnique.setStudentPhoneNum(stu.getStudentPhoneNum());
			stuUnique.setStudentName(stu.getStudentName());
			insert(stuUnique);
		}else{
			String oraPhone = stuOraUnique.getStudentPhoneNum();
			//判断手机号是否一致
			if(!oraPhone.equals(stuPhoneNum)){//系统内已存在将手机号修改过来
				stu.setStudentPhoneNum(oraPhone);
				SocietyStudent oraStu = studentMapper.selectOneByPhoneNumAndSchool(stu.getStudentPhoneNum(),stu.getOwnerSchoolId());
				if(oraStu!=null && !oraStu.getId().equals(stu.getId())){
					throw new BusinessException(stu.getOwnerSchoolName() + "存在学员" + oraStu.getStudentName() + "与本学员重复!");
				}else{
					studentMapper.updateById(stu);
				}

			}
		}


	}

    public List<SocietyStudentUnique> selectUserByCreateTime(String createTime) {
		return societyStudentUniqueMapper.selectUserByCreateTime(createTime);
    }
}
