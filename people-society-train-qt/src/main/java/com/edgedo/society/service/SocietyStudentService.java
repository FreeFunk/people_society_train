package com.edgedo.society.service;
		
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.constant.ThirdPartyReqKey;
import com.edgedo.common.constant.ThirdPartyType;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.MD5;
import com.edgedo.common.util.RedisUtil;
import com.edgedo.face.entity.FaceMatchInfoExt;
import com.edgedo.society.constant.RedisKeyConstant;
import com.edgedo.society.constant.SocietyTrainContant;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentAndCourse;
import com.edgedo.society.entity.SocietyStudentUnique;
import com.edgedo.society.exception.StudentNoLearnFaceException;
import com.edgedo.society.mapper.SocietySchoolClassAndCourseMapper;
import com.edgedo.society.mapper.SocietySchoolClassAndStudentMapper;
import com.edgedo.society.mapper.SocietySchoolClassMapper;
import com.edgedo.society.mapper.SocietyStudentMapper;
import com.edgedo.society.queryvo.SocietyStudentQuery;
import com.edgedo.society.queryvo.SocietyStudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentService {

	@Autowired
	RedisUtil redisUtil;
	@Value("${app.commonTokenTimeOutSec}")
	private Integer commonTokenTimeOutSec;
	
	@Autowired
	private SocietyStudentMapper societyStudentMapper;
	@Autowired
	private SocietySchoolClassAndCourseMapper classAndCourseMapper;
	@Autowired
	private SocietySchoolClassAndStudentService classAndStudentService;
	@Autowired
	private SocietyStudentAndCourseService studentAndCourseService;
	@Autowired
	private SocietySchoolClassService schoolClassService;

	public List<SocietyStudentView> listPage(SocietyStudentQuery societyStudentQuery){
		List list = societyStudentMapper.listPage(societyStudentQuery);
		societyStudentQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudent societyStudent) {
		societyStudent.setId(Guid.guid());
		societyStudentMapper.insert(societyStudent);
		return "";
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertOrUpdateInfoAndEncodePwd(SocietyStudent societyStudent) {
		String id = societyStudent.getId();
		if(id != null && !id.equals("")){
			societyStudent.setIsUpPwd("1");
			//密码加密
			String password = societyStudent.getPassword();
			String encodePwd = MD5.encode(MD5.encode(password) + societyStudent.getId());
			societyStudent.setPassword(encodePwd);
			societyStudentMapper.updateById(societyStudent);
		}else{
			societyStudent.setId(Guid.guid());
			societyStudent.setCreateTime(new Date());
			societyStudent.setDataState("1");
			societyStudent.setIsUpPwd("1");
			//密码加密
			String password = societyStudent.getPassword();
			String encodePwd = MD5.encode(MD5.encode(password) + societyStudent.getId());
			societyStudent.setPassword(encodePwd);
			societyStudentMapper.insert(societyStudent);
		}
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudent societyStudent) {
		societyStudentMapper.updateById(societyStudent);
		return "";
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietyStudentAndCourse updateStuAndClassInfo(SocietyStudentUnique societyStudent,SocietySchoolClass schoolClass) {
		SocietyStudentAndCourse studentAndCourse = null;
		if(schoolClass != null){
			//根据班级查询课程
			String classId = schoolClass.getId();
			List<String> courseIdList = classAndCourseMapper.selectCourseIdByClass(classId);
			String schId = schoolClass.getOwnerSchoolId();
			String stuIdCardNum = societyStudent.getId();
			SocietyStudent student = selectOneByIdCardNumAndSchool(stuIdCardNum,schId);
			if(student != null){
				//插入学员与班级的关联
				classAndStudentService.insertClassAndStudentRelation(student,schoolClass);
				//插入学员与课程的关联
				studentAndCourse = studentAndCourseService.insertCourseAndStudentRelation(student,courseIdList,classId);
			}
			//统计该班级的人数
			int totalStuNum = classAndStudentService.countStudentByClassId(classId);
			schoolClass.setClassPersonNum(totalStuNum);
			schoolClassService.update(schoolClass);
			//更新学员信息
			societyStudentMapper.updateById(student);
		}

		return studentAndCourse;
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudent societyStudent) {
		societyStudentMapper.updateAllColumnById(societyStudent);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudent loadById(String id) {
		return societyStudentMapper.selectById(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据学校id  用户类型， openid查询
	 * @Date 2020/5/4 8:52
	 **/
    public SocietyStudent selectByOpenIdAndTypeSchool(String schoolId, ThirdPartyType thirdPartyType, String openId) {
		if(thirdPartyType.equals(ThirdPartyType.wechat)){
			return societyStudentMapper.selectStuBySchoolIdAndWxOpenId(schoolId,openId);
		}else if(thirdPartyType.equals(ThirdPartyType.aboc)){
			return societyStudentMapper.selectStuBySchoolIdAndAbocOpenId(schoolId,openId);
		}
		return null;
    }

    /**
     * @Author WangZhen
     * @Description  检查学员是否存在人脸记录
     * @Date 2020/5/4 15:49
     **/
    public FaceMatchInfoExt checkLearnFace(SocietyStudentUnique student) throws StudentNoLearnFaceException {

		String key = RedisKeyConstant.STUDENT_LEARN_FACE_PREFIX + student.getId();
		Object obj = redisUtil.get(key);
		FaceMatchInfoExt ext = (FaceMatchInfoExt)obj;

		if(ext==null){
			//没有人脸直接抛出
			throw new StudentNoLearnFaceException();
		}else{
			if(!ext.isPass()){
				throw new StudentNoLearnFaceException();
			}
			//TODO: 如果不需要随机人脸校验了把这个打开让失效时间实时更新
//			redisUtil.expire(key, SocietyTrainContant.SUC_FACE_REDIS_KEEP_TIME);
			return ext;
		}
    }

    /**
     * @Author WangZhen
     * @Description 清理学员的人脸识别状态
     * @Date 2020/7/9 16:10
     **/
	public void clearStudentFaceCatch(SocietyStudentUnique student) {
		String key = RedisKeyConstant.STUDENT_LEARN_FACE_PREFIX + student.getId();
		redisUtil.del(key);
	}


	/**
	 * @Author WangZhen
	 * @Description  检查学员是否存在人脸记录
	 * @Date 2020/5/4 15:49
	 **/
	public String getLearnFace(SocietyStudent student) {
		String key = RedisKeyConstant.STUDENT_LEARN_FACE_PREFIX + student.getId();
		Object obj = redisUtil.get(key);
		if(obj==null){
			//没有人脸直接抛出
			return null;
		}else{
			return (String)obj;
		}
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据手机号查询一条学员信息
	 * @Date 2020/5/5 14:55
	 **/
	public SocietyStudent selectOneByPhoneNumAndSchool(String studentPhoneNum,String ownerSchoolId){
		SocietyStudent student = societyStudentMapper.selectOneByPhoneNumAndSchool(studentPhoneNum,ownerSchoolId);
		return student;
	}


	/**
	 * @Author ZhangCC
	 * @Description 根据身份证号和学校查询学员
	 * @Date 2020/5/26 16:53
	 **/
	public SocietyStudent selectOneByIdCardNumAndSchool(String studentIdCardNum,String ownerSchoolId){
		Map<String,Object> param = new HashMap<>();
		param.put("studentIdCardNum",studentIdCardNum);
		param.put("ownerSchoolId",ownerSchoolId);
		SocietyStudent student = societyStudentMapper.selectOneByIdCardNumAndSchool(param);
		return student;
	}

	/**
	 * @Author ZhangCC
	 * @Description 更新学员实名认证信息
	 * @Date 2020/5/7 19:38
	 **/
	/*public int updateStudentRealName(SocietyStudent student){
		clearStudentCatch(student);
		return societyStudentMapper.updateStudentRealName(student);
	}*/

	/**
	 * @Author WangZhen
	 * @Description 清理缓存中的用户
	 * @Date 2020/5/20 15:48
	 **/
	public void clearStudentCatch(SocietyStudent student){
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
	 * @Description 根据token和学校获得学生信息
	 * @Date 2020/5/11 16:11
	 **/
	public SocietyStudentView selectByAccessTokenAndSch(String accessToken, String schoolId) {
		return societyStudentMapper.selectByAccessTokenAndSch(accessToken,schoolId);
	}

	/**
	 * @Author WangZhen
	 * @Description 查询部分字段， 根据学校id  用户类型， openid查询
	 * @Date 2020/5/12 20:01
	 **/
	/*public SocietyStudent selectByOpenIdAndTypeSchoolSimple(
			String schoolId, ThirdPartyType thirdPartyType, String openId) {
		//实名认证或者退出的时候清空
		String key = RedisKeyConstant.selectByOpenIdAndTypeSchoolSimple
				+ schoolId + "_" + thirdPartyType + "_" + openId;
		Object obj = redisUtil.get(key,commonTokenTimeOutSec);
		if(obj!=null){
			return (SocietyStudent)obj;
		}
		SocietyStudent stu = null;
		if(thirdPartyType.equals(ThirdPartyType.wechat)){
			stu =  societyStudentMapper.selectStuBySchoolIdAndWxOpenIdSimple(schoolId,openId);
		}else if(thirdPartyType.equals(ThirdPartyType.aboc)){
			stu = societyStudentMapper.selectStuBySchoolIdAndAbocOpenIdSimple(schoolId,openId);
		}
		if(stu!=null){
			redisUtil.set(key,stu,commonTokenTimeOutSec);
		}
		return stu;
	}*/

	/**
	 * @Author WangZhen
	 * @Description 根据accesstoken和学校id查询学员
	 * @Date 2020/5/20 15:34
	 **/
/*	public SocietyStudent selectByAccessTokenAndSchSimple(String accessToken, String schoolId) {
		//实名认证或者退出的时候清空
		String key = RedisKeyConstant.selectByAccessTokenAndSchSimple + accessToken + "_" + schoolId;
		Object obj = redisUtil.get(key,commonTokenTimeOutSec);
		if(obj!=null){
			return (SocietyStudent)obj;
		}
		SocietyStudent stu = societyStudentMapper.selectByAccessTokenAndSchSimple(accessToken,schoolId);
		if(stu!=null){
			redisUtil.set(key,stu,commonTokenTimeOutSec);
		}
		return stu;
	}*/

	/**
	 * @Author ZhangCC
	 * @Description 清空非微信用户的accessToken
	 * @Date 2020/5/12 20:16
	 **/
	public void deleteStuAccessToken(String id){
		societyStudentMapper.deleteStuAccessToken(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 清空微信用户的openId
	 * @Date 2020/5/12 20:16
	 **/
	public void deleteStuWxOpenId(String id){
		societyStudentMapper.deleteStuWxOpenId(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 查询所有
	 * @Date 2020/7/15 20:52
	 **/
    public List<SocietyStudentView> listAll() {
		return societyStudentMapper.listAll();
    }
}
