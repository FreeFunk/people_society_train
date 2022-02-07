package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.Test;
import com.edgedo.pay.PayOperatorService;
import com.edgedo.society.entity.*;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.mapper.SocietyStudentAndCourseMapper;
import com.edgedo.society.mapper.SysWxPayRecordMapper;
import com.edgedo.society.queryvo.SocietyStudentAndCourseQuery;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import com.edgedo.society.service.SysWxPayRecordService;
//import com.edgedo.wxpayclient.ISysWxPayClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentAndCourseService implements PayOperatorService {
	
	
	@Autowired
	private SocietyStudentAndCourseMapper societyStudentAndCourseMapper;
	@Autowired
	private SocietySchoolCourseService schoolCourseService;
	@Autowired
	private SocietySchoolClassAndStudentService classAndStudentService;
	@Autowired
	private SocietyStudentAndNodeService studentAndNodeService;
	@Autowired
	private SocietyStudentCertificateService certificateService;


	@Autowired
	private SysWxPayRecordService wxPayRecordService;
	/*@Autowired
	private ISysWxPayClientService iSysWxPayClientService;*/
	@Autowired
	private SysWxPayRecordMapper sysWxPayRecordMapper;

	//微信服务的支付回调地址
	@Value("${wxpay.rootUrl}")
	private String wxPayRootUrl;
	//微信支付成功跳转的地址
	@Value("${wxpay.successUrl}")
	private String wxPaySuccessUrl;
	//微信支付失败跳转的地址
	@Value("${wxpay.failUrl}")
	private String wxPayFailUrl;

	public List<SocietyStudentAndCourseView> listPage(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List list = societyStudentAndCourseMapper.listPage(societyStudentAndCourseQuery);
		societyStudentAndCourseQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentAndCourse societyStudentAndCourse) {
		societyStudentAndCourse.setId(Guid.guid());
		societyStudentAndCourseMapper.insert(societyStudentAndCourse);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentAndCourse societyStudentAndCourse) {
		societyStudentAndCourseMapper.updateById(societyStudentAndCourse);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentAndCourse societyStudentAndCourse) {
		societyStudentAndCourseMapper.updateAllColumnById(societyStudentAndCourse);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentAndCourseMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentAndCourseMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentAndCourse loadById(String id) {
		return societyStudentAndCourseMapper.selectById(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据主键和学生id查询
	 * @Date 2020/5/4 11:02
	 **/
   /* public SocietyStudentAndCourseView loadByIdAndStuId(String id, String studentId) {
		return societyStudentAndCourseMapper.loadByIdAndStuId(id,studentId);
    }*/

    /**
     * @Author ZhangCC
     * @Description 根据学员查询关联的课程
     * @Date 2020/5/5 21:02
     **/
    public List<String> selectCourseIdListByStu(String studentId,String ownerSchoolId){
		Map<String,Object> param = new HashMap<>();
		param.put("studentId",studentId);
		param.put("ownerSchoolId",ownerSchoolId);
    	return societyStudentAndCourseMapper.selectCourseIdListByStu(param);
	}

	/**
	 * @Author WangZhen
	 * @Description 更新课程的学习进度
	 * @Date 2020/5/7 9:49
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public int updateLearnProgress(
    		String id, int finishNodeNum,
			BigDecimal courseProgress) {
		int progress = courseProgress.intValue();
		SocietyStudentAndCourse param = new SocietyStudentAndCourse();
		param.setId(id);
		param.setFinishNodeNum(finishNodeNum);
		param.setCourseProgress(courseProgress);
		if(progress>=100){
			param.setLearnIsFinished("1");
			param.setLearnFinishTime(new Date());
		}
		return societyStudentAndCourseMapper.updateById(param);
    }

    /**
     * @Author ZhangCC
     * @Description 查询学员关联的课程
     * @Date 2020/5/7 16:34
     **/
    public List<SocietyStudentAndCourseView> stuCourseByIdCardListPage(SocietyStudentAndCourseQuery query){
		List list = societyStudentAndCourseMapper.stuCourseByIdCardListPage(query);
		query.setList(list);
		return list;
	}

	/**
	 * @Author WangZhen
	 * @Description 更新
	 * @Date 2020/5/10 10:45
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public int updateLastLearnNode(SocietyStudentAndCourseView stuCourse) {
		return societyStudentAndCourseMapper.updateLastLearnNode(stuCourse);
    }

    /**
     * @Author ZhangCC
     * @Description 查询学员未完成的关联课程
     * @Date 2020/5/12 19:57
     **/
    public SocietyStudentAndCourseView selectOneStuCourseByCourseId(String studentId,String courseId){
		Map<String,Object> param = new HashMap<>();
		param.put("studentId",studentId);
		param.put("courseId",courseId);
		return societyStudentAndCourseMapper.selectOneStuCourseByCourseId(param);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询学员一条完成的学习
	 * @Date 2020/5/19 9:30
	 **/
	public SocietyStudentAndCourseView selectOneFinishedStuCourse(String courseId,String studentId){
		Map<String,Object> param = new HashMap<>();
		param.put("courseId",courseId);
		param.put("studentId",studentId);
		return societyStudentAndCourseMapper.selectOneFinishedStuCourse(param);
	}

	/**
	 * @Author ZhangCC
	 * @Description 插入学员与课程的关联
	 * @Date 2020/5/5 8:24
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietyStudentAndCourse insertCourseAndStudentRelation(SocietyStudent student, List<String> courseIdList, String classId){
		//判断学生是否在这个班级
		SocietyStudentAndCourse studentAndCourse = new SocietyStudentAndCourse();
		studentAndCourse.setOwnerSchoolId(student.getOwnerSchoolId());
		studentAndCourse.setClassId(classId);
		studentAndCourse.setStudentId(student.getId());
		studentAndCourse.setStudentName(student.getStudentName());
		studentAndCourse.setStudentIdCardNum(student.getStudentIdCardNum());
		studentAndCourse.setDataState("1");
		studentAndCourse.setFinishNodeNum(0);
		studentAndCourse.setCourseProgress(new BigDecimal(0));
		studentAndCourse.setLearnIsFinished("0");
		Map<String,Object> map = new HashMap<>();
		map.put("studentId",student.getId());
		map.put("classId",classId);
		for(int i=0;i<courseIdList.size();i++){
			SocietySchoolCourse course = schoolCourseService.loadById(courseIdList.get(i));
			if(course != null){
				String courseId = course.getId();
				//判断该班级学员是否关联该课程
				map.put("courseId",courseId);
				int count = societyStudentAndCourseMapper.countByMap(map);
				if(count==0){
					studentAndCourse.setId(Guid.guid());
					studentAndCourse.setCourseId(course.getId());
					studentAndCourse.setCourseName(course.getCourseName());
					studentAndCourse.setCourseImage(course.getCourseImage());
					studentAndCourse.setTotalLessons(course.getTotalLessons());
					BigDecimal coursePrice = course.getCoursePrice();
					//判断课程是否是免费
					if(coursePrice==null || coursePrice.compareTo(BigDecimal.ZERO)==0){
						studentAndCourse.setCoursePrice(new BigDecimal(0));
						studentAndCourse.setCourseOrgPrice(course.getCourseOrgPrice());
						studentAndCourse.setPayState("1");
					}else {
						studentAndCourse.setCoursePrice(course.getCoursePrice());
						studentAndCourse.setCourseOrgPrice(course.getCourseOrgPrice());
						studentAndCourse.setPayState("0");
					}
					societyStudentAndCourseMapper.insert(studentAndCourse);
				}
			}
		}
		return studentAndCourse;
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 购买课程预支付
	 *@DateTime: 2020/6/11 10:50
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateWxWapPrepayCourse(String stuCourseId,String ip, String userAgent, String openId) {
		SocietyStudentAndCourse studentAndCourse = loadById(stuCourseId);
		//封装微信预支付记录
		SysWxPayRecord wxPayRecord = genPrepayRec(studentAndCourse,ip,openId);
		//关联预支付id
		studentAndCourse.setPrepayId(wxPayRecord.getPrepayId());
		update(studentAndCourse);
		try {
			/*调用微信的预支付接口*/
			String result = "";//iSysWxPayClientService.wxPrePay(JSON.toJSONString(wxPayRecord),userAgent);
			System.out.println("result====="+result);
			JSONObject jsonObj = JSON.parseObject(result);
			boolean success = (boolean)jsonObj.get("success");
			SortedMap<String,Object> responseMap = new TreeMap<String,Object>();
			if(!success){
				String errMsg = (String)jsonObj.get("errMsg");
				System.out.println("预支付接口======"+errMsg);
			}else{
				JSONObject jsonObject = (JSONObject)jsonObj.get("data");
				Set<String> keySet = jsonObject.keySet();
				for(String key : keySet){
					responseMap.put(key,jsonObject.get(key));
				}
			}
			System.out.println("responseMap===="+responseMap);
			if("SUCCESS".equals(responseMap.get("result_code")) ){
				/*插入预支付成功的记录*/
				wxPayRecord.setPrepayId(responseMap.get("prepay_id")+"");
				wxPayRecord.setPrepayTime(new Date());
				sysWxPayRecordMapper.insert(wxPayRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wxPayRecord.getId();
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 封装微信预支付记录
	 *@DateTime: 2020/6/11 10:48
	 */
	private SysWxPayRecord genPrepayRec(SocietyStudentAndCourse studentAndCourse, String ip,String openId){
		/*生成预支付订单*/
		String prepayId = Guid.guid();
		SysWxPayRecord wxPayRecord = new SysWxPayRecord();
		wxPayRecord.setId(prepayId);
		wxPayRecord.setOutTradeNo(prepayId);
		wxPayRecord.setAttach(prepayId);
		wxPayRecord.setSpbillCreateIp(ip);
		wxPayRecord.setOpenid(openId);
		wxPayRecord.setPayResult("CREATE");
		wxPayRecord.setPayType("WX");
		wxPayRecord.setTradeType("JSAPI");

		/*动态设置和课程相关的信息*/
		wxPayRecord.setBillId(studentAndCourse.getId());
		wxPayRecord.setBillType("com.edgedo.society.service.SocietyStudentAndCourseService");
		wxPayRecord.setTotalFee(new BigDecimal(studentAndCourse.getCoursePrice().doubleValue()*100));
		String body = studentAndCourse.getCourseName() + "课程购买";
		wxPayRecord.setBody(body);
		//异步接收微信支付结果通知的回调地址，
		String notifyUrl = wxPayRootUrl+"/pay/apppay.jsn";
		//支付成功跳转的页面地址
		String successUrl = wxPaySuccessUrl;
		//支付失败跳转的页面地址
		String failUrl = wxPayFailUrl;

		wxPayRecord.setNotifyUrl(notifyUrl);
		wxPayRecord.setSuccessUrl(successUrl);
		wxPayRecord.setFailUrl(failUrl);
		wxPayRecord.setPrepayTime(new Date());
		return  wxPayRecord;
	}

	/**
	 * 支付成功之后给用户充值
	 * @param payRecord 预支付订单
	 * @return
	 * @throws
	 */
	@Override
	public String updatePaySuccess(SysWxPayRecord payRecord){
		String billId = payRecord.getBillId();
		SocietyStudentAndCourse studentAndCourse = loadById(billId);
		studentAndCourse.setPayState("1");
		studentAndCourse.setPayTime(payRecord.getPayTime());
		studentAndCourse.setPrepayId(payRecord.getPrepayId());
		studentAndCourse.setFee(studentAndCourse.getCoursePrice().multiply(new BigDecimal(0.006)));
		studentAndCourse.setRealGetMoney(studentAndCourse.getCoursePrice().multiply(new BigDecimal(1-0.006)));
		update(studentAndCourse);
		return null;
	}

	/**
	 * @Author WangZhen
	 * @Description 根据stuCourseId，和身份证号码获得学习的课程
	 * @Date 2020/7/14 7:45
	 **/
    public SocietyStudentAndCourseView loadByIdAndStuIdCardNum(String stuCourseId, String idCardNum) {
		return societyStudentAndCourseMapper.loadByIdAndStuIdCardNum(stuCourseId,idCardNum);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据身份证号和课程id查询学生课程
	 * @Date 2020/7/14 8:29
	 **/
	public SocietyStudentAndCourse selectOneByCourseIdAndIdCarNum(String idCardNum, String courseId) {
		return  societyStudentAndCourseMapper.selectOneByCourseIdAndIdCarNum(idCardNum,courseId);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据身份证号统计课程数量
	 * @Date 2020/7/14 9:18
	 **/
	public int countCourseByStuIdCardNum(String stuIdCardNum) {
		return societyStudentAndCourseMapper.countCourseByStuIdCardNum(stuIdCardNum);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据身份证号和课程id查询已经完成的课程
	 * @Date 2020/7/14 11:00
	 **/
	public SocietyStudentAndCourse selectOneFinishedByIdCardAndCourse(String courseId, String idCardNum) {
		return societyStudentAndCourseMapper.selectOneFinishedByIdCardAndCourse(courseId,idCardNum);
	}

	/**
	 * @Author WangZhen
	 * @Description 课程进度
	 * @Date 2020/7/15 10:37
	 **/
    public void updateFreshLearnInfo(String stuCourseId,String courseId,SocietyStudent student) {
//开始设置学生本次课程的学习进度
		//拿到课程中的课程章节数
		SocietySchoolCourse course = schoolCourseService.loadById(courseId);
		int totalCount = course.getTotalLessons();
		//统计已经完结的章节数据
		int finishCount = studentAndNodeService.countFinishNodeOfStuCourse(stuCourseId);

		//执行更新db
		int rows = updateLearnProgress(
				stuCourseId,finishCount,new BigDecimal(finishCount*100.0/totalCount));
		if(finishCount>=totalCount){
			//判断一下习题是否有没合格的
			int practiseUnpassNum = studentAndNodeService.countPractiseUnPassNodeOfStuCourse(stuCourseId);
			if(practiseUnpassNum==0){
				//拿证书
				certificateService.insertStudentCert(courseId,student);
			}
		}
    }

}
