package com.edgedo.society.service;
		
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BusinessException;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.RedisUtil;
import com.edgedo.face.entity.FaceMatchInfoExt;
import com.edgedo.society.constant.RedisKeyConstant;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietyStudentStudyProcessFaceMapper;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessFaceQuery;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessFaceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentStudyProcessFaceService {
	//存放文件夹的目录
	@Value("${app.fileForder}")
	String fileForder;
	@Autowired
	RedisUtil redisUtil;

	@Autowired
	private SocietyStudentStudyProcessFaceMapper societyStudentStudyProcessFaceMapper;
	@Autowired
	private SocietyStudentAndCourseService studentAndCourseService;
	@Autowired
	private SocietySchoolCourseNodeService courseAndNodeService;

	public List<SocietyStudentStudyProcessFaceView> listPage(SocietyStudentStudyProcessFaceQuery societyStudentStudyProcessFaceQuery){
		List list = societyStudentStudyProcessFaceMapper.listPage(societyStudentStudyProcessFaceQuery);
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
	public int deleteByIds(List<String> ids) {
		
		return societyStudentStudyProcessFaceMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentStudyProcessFace loadById(String id) {
		return societyStudentStudyProcessFaceMapper.selectById(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 将人脸记录存放到数据库中
	 * 1.先将人脸图片从临时文件放到目标文件夹
	 * 2.返回路径存入到人脸图片记录里面
	 * 3.将记录存入到数据库中
	 * TODO: 有时间改成队列中进行
	 * @Date 2020/5/7 14:56
	 **/
    public void insertFaceMatch(
    		SocietyStudentUnique stu ,
			FaceMatchInfoExt faceMatchInfo,
			String schId,SocietySchoolCourseNode node,String stuCourseId) {

    	String temFacePath = faceMatchInfo.getFace2();
    	String realTemFaceFilePath = fileForder +
				FileUtil.changeWebPathToFilePath(temFacePath);
    	String targetForder = fileForder + File.separator + schId + File.separator + "faceimg";
		try {

			String faceUrl = "/" + schId + "/faceimg" + FileUtil.saveFile(
                    new File(realTemFaceFilePath),
                    targetForder,
        true
            );
			SocietyStudentAndCourse stuAndCourse = studentAndCourseService.loadById(stuCourseId);

			SocietyStudentStudyProcessFace processFace = new SocietyStudentStudyProcessFace();
			processFace.setId(faceMatchInfo.getId());
			processFace.setCreateTime(new Date());
			processFace.setFaceImageUrl(faceUrl);
			processFace.setFaceScore(new BigDecimal(faceMatchInfo.getScore()));
			processFace.setStudentId(stu.getStudentIdCardNum());
			processFace.setStudentName(stu.getStudentName());
			processFace.setDataState("1");
			if(stuAndCourse!=null){
				processFace.setStuCourseId(stuCourseId);
				//schoolid  schoolname 目前没啥用不设置
				processFace.setOwnerSchoolId(stuAndCourse.getOwnerSchoolId());
			}
			if(node!=null){
				processFace.setOwnerNodeId(node.getId());
				processFace.setOwnerNodeName(node.getNodeName());
			}


			societyStudentStudyProcessFaceMapper.insert(processFace);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("人脸转存失败");
		}
	}

	/**
	 * @Author WangZhen
	 * @Description 设置人脸识别归属那条学习记录
	 * @Date 2020/5/7 15:25
	 **/
	public void updateFaceRecGiveToLearnProgress(
			FaceMatchInfoExt faceMatchInfoExt, SocietyStudentStudyProcess process,
			SocietyStudentUnique student) {
		//对比下存入人脸记录的nodeId 和 process是否一致
		//如果nodeid能对上那么更新progress的信息上去否则不修改
		String proNodeId = process.getOwnerNodeId();
		String id = faceMatchInfoExt.getId();
		SocietyStudentStudyProcessFace processFace = societyStudentStudyProcessFaceMapper.selectById(id);
		String faceNodeId = processFace.getOwnerNodeId();


		processFace.setFaceType("learn");
		if(proNodeId.equals(faceNodeId)){
			processFace.setOwnerCourseId(process.getOwnerCourseId());
			processFace.setOwnerCourseName(process.getOwnerCourseName());
		}
		processFace.setOwnerSchoolId(process.getOwnerSchoolId());
		processFace.setOwnerSchoolName(process.getOwnerSchoolName());
		processFace.setOwnerStudyProcessId(process.getId());
		societyStudentStudyProcessFaceMapper.updateById(processFace);
		String key = RedisKeyConstant.STUDENT_LEARN_FACE_PREFIX + student.getId();
		long expire = redisUtil.getExpire(key);
		faceMatchInfoExt.setoId(process.getId());
		redisUtil.set(key,faceMatchInfoExt,expire);
	}

	/**
	 * @Author WangZhen
	 * @Description 更具学生课程，和课程节点统计人脸数量
	 * @Date 2020/7/30 15:58
	 **/
	public int selectCountByStuCourseAndNode(String stuCourseId, String courseNodeId) {
		return societyStudentStudyProcessFaceMapper.selectCountByStuCourseAndNode(stuCourseId,courseNodeId);
	}



	public SocietyStudentStudyProcessFace selectByCourseNodeIdAndStuCourseIdEnd(String courseNodeId, String stuCourseId) {
		return societyStudentStudyProcessFaceMapper.selectByCourseNodeIdAndStuCourseIdEnd(courseNodeId,stuCourseId);
	}

	public SocietyStudentStudyProcessFace selectByCourseNodeIdAndStuCourseIdStart(String courseNodeId, String stuCourseId) {
		return societyStudentStudyProcessFaceMapper.selectByCourseNodeIdAndStuCourseIdStart(courseNodeId,stuCourseId);
	}
}
