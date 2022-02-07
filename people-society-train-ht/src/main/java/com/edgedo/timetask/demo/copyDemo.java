package com.edgedo.timetask.demo;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.service.*;
import com.edgedo.tyiyunoosclient.ISysTyiyunCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
//@Component
public class copyDemo {
    @Autowired
    private SocietySchoolService societySchoolService;
    @Autowired
    private SocietySchoolMajorService societySchoolMajorService;
    @Autowired
    private SocietySchoolCourseService societySchoolCourseService;
    @Autowired
    private SocietySchoolCourseClsService societySchoolCourseClsService;
    @Autowired
    private SocietySchoolCourseNodeService societySchoolCourseNodeService;

    @Value("${app.tcloudDomain}")
    private String tcloudDomain;
    @Autowired
    private ISysTyiyunCloudStorageService sysTyiyunCloudStorageService;

    @Autowired
    private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
    @Autowired
    private SocietySchoolCourseNodeOptionService societySchoolCourseNodeOptionService;

    boolean flag = true;

//    @Scheduled(cron = "0/5 * * * * ?")
    public void copy(){
        if(flag){
            flag = false;
            String schoolId = "fnqahzzpc";
            String majorId = "883a465eedba41f4b58fdfa23689dce1";
            String courseId = "4d242d6c-d9ca-4d36-9bdd-6c0bd2b9a8c8";
            String courseClsId = "83b23c9c94f0486f8939e4f6b4ef7c15";
            String userId = "9a17458061c64f94aebaa8ecc0eed336";
            String userName ="抚宁区奥辉纸制品厂";

            //原来的别的学校章节课程
            String oldCourseId = "222aab3740eb48c48637503c4ffdd937";

            SocietySchool societySchool = societySchoolService.loadById(schoolId);
            SocietySchoolMajor societySchoolMajor = societySchoolMajorService.loadById(majorId);
            SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
            SocietySchoolCourseCls societySchoolCourseCls = societySchoolCourseClsService.loadById(courseClsId);

            //被复制
            SocietySchoolCourse societySchoolCourseOld = societySchoolCourseService.loadById(oldCourseId);
            List<SocietySchoolCourseNode> courseNodeList =
                    societySchoolCourseNodeService.selectByCourseIdAndSchoolIdAll(societySchoolCourseOld.getOwnerSchoolId(),societySchoolCourseOld.getId());

            for(SocietySchoolCourseNode societySchoolCourseNode : courseNodeList){
                int numCopy = societySchoolCourseNodeService.selectByCourseNameAndId(courseId);
                if(numCopy==0){
                    SocietySchoolCourseNode societySchoolCourseNodeNew = new SocietySchoolCourseNode();
                    societySchoolCourseNodeNew.setId(Guid.guid());
                    societySchoolCourseNodeNew.setCreateTime(new Date());
                    societySchoolCourseNodeNew.setCreateUserId(userId);
                    societySchoolCourseNodeNew.setCreateUserName(userName);
                    societySchoolCourseNodeNew.setOwnerSchoolId(societySchool.getId());
                    societySchoolCourseNodeNew.setOwnerSchoolName(societySchool.getSchoolName());
                    societySchoolCourseNodeNew.setOwnerMajorId(societySchoolMajor.getId());
                    societySchoolCourseNodeNew.setOwnerMajorName(societySchoolMajor.getMajorName());
                    societySchoolCourseNodeNew.setOwnerCourseClsId(societySchoolCourseCls.getId());
                    societySchoolCourseNodeNew.setOwnerCourseClsName(societySchoolCourseCls.getCourseClsName());
                    societySchoolCourseNodeNew.setOwnerCourseId(societySchoolCourse.getId());
                    societySchoolCourseNodeNew.setOwnerCourseName(societySchoolCourse.getCourseName());

                    societySchoolCourseNodeNew.setNodeName(societySchoolCourseNode.getNodeName());
                    societySchoolCourseNodeNew.setNodeTimeLength(societySchoolCourseNode.getNodeTimeLength());
                    societySchoolCourseNodeNew.setQuestionNum(societySchoolCourseNode.getQuestionNum());
                    societySchoolCourseNodeNew.setOrderNum(societySchoolCourseNode.getOrderNum());
                    societySchoolCourseNodeNew.setShState(societySchoolCourseNode.getShState());
                    societySchoolCourseNodeNew.setDataState("1");

                    String[] urlOld = societySchoolCourseNode.getFileUrl().split(tcloudDomain+"/");
                    String copyOld = urlOld[1];
                    String[] urlNew = urlOld[1].split("/");
                    String copyNew = urlNew[0]+"/"+societySchool.getId()+"/diangongchujikechengzhangjieliebiao/"+urlNew[urlNew.length-1];

                    //1.先将视频复制
                    sysTyiyunCloudStorageService.copyObject(copyOld,copyNew);
                    //2.获取路径
                    String fileUrlNew = tcloudDomain+"/"+copyNew;
                    societySchoolCourseNodeNew.setFileUrl(fileUrlNew);
                    societySchoolCourseNodeService.insert(societySchoolCourseNodeNew);
                    //1.携带题目  根据课程id 学校id 章节id 专业id 分类id查出list
                    List<SocietySchoolCourseNodeQuestion> list = societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeIdList(
                            societySchoolCourseNode.getOwnerCourseId(),societySchoolCourseNode.getOwnerSchoolId(),societySchoolCourseNode.getId());

                    for(SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion : list){
                        SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestionNew = new SocietySchoolCourseNodeQuestion();
                        societySchoolCourseNodeQuestionNew.setId(Guid.guid());
                        societySchoolCourseNodeQuestionNew.setCreateUserId(userId);
                        societySchoolCourseNodeQuestionNew.setCreateTime(new Date());
                        societySchoolCourseNodeQuestionNew.setCreateUserName(userName);
                        societySchoolCourseNodeQuestionNew.setOwnerSchoolId(societySchool.getId());
                        societySchoolCourseNodeQuestionNew.setOwnerSchoolName(societySchool.getSchoolName());
                        societySchoolCourseNodeQuestionNew.setOwnerMajorId(societySchoolMajor.getId());
                        societySchoolCourseNodeQuestionNew.setOwnerMajorName(societySchoolMajor.getMajorName());
                        societySchoolCourseNodeQuestionNew.setOwnerCourseClsId(societySchoolCourseCls.getId());
                        societySchoolCourseNodeQuestionNew.setOwnerCourseClsName(societySchoolCourseCls.getCourseClsName());
                        societySchoolCourseNodeQuestionNew.setOwnerCourseId(societySchoolCourse.getId());
                        societySchoolCourseNodeQuestionNew.setOwnerCourseName(societySchoolCourse.getCourseName());

                        societySchoolCourseNodeQuestionNew.setOwnerNodeId(societySchoolCourseNodeNew.getId());
                        societySchoolCourseNodeQuestionNew.setOwnerNodeName(societySchoolCourseNodeNew.getNodeName());
                        societySchoolCourseNodeQuestionNew.setQuestionName(societySchoolCourseNodeQuestion.getQuestionName());
                        societySchoolCourseNodeQuestionNew.setQuestionType(societySchoolCourseNodeQuestion.getQuestionType());
                        societySchoolCourseNodeQuestionNew.setQuestionScore(societySchoolCourseNodeQuestion.getQuestionScore());
                        societySchoolCourseNodeQuestionNew.setQuestionAnalysis(societySchoolCourseNodeQuestion.getQuestionAnalysis());
                        societySchoolCourseNodeQuestionNew.setQuestionAnswer(societySchoolCourseNodeQuestion.getQuestionAnswer());
                        int num = societySchoolCourseNodeQuestionService.countNodeQuestionNum(societySchoolCourseNodeNew.getId());
                        societySchoolCourseNodeQuestionNew.setOrderNum(new BigDecimal(num+1));
                        societySchoolCourseNodeQuestionService.insertCopy(societySchoolCourseNodeQuestionNew);
                        //1.根据原有习题id查出相关的选项 list
                        List<SocietySchoolCourseNodeOption> optionList = societySchoolCourseNodeOptionService.selectOptionByOptionList(societySchoolCourseNode.getOwnerSchoolId(),
                                societySchoolCourseNodeQuestion.getOwnerNodeId(),societySchoolCourseNodeQuestion.getId());
                        for(SocietySchoolCourseNodeOption societySchoolCourseNodeOption : optionList){
                            SocietySchoolCourseNodeOption societySchoolCourseNodeOptionNew = new SocietySchoolCourseNodeOption();
                            societySchoolCourseNodeOptionNew.setId(Guid.guid());
                            societySchoolCourseNodeOptionNew.setCreateTime(new Date());
                            societySchoolCourseNodeOptionNew.setCreateUserName(userName);
                            societySchoolCourseNodeOptionNew.setCreateUserId(userId);
                            societySchoolCourseNodeOptionNew.setOwnerSchoolId(societySchool.getId());
                            societySchoolCourseNodeOptionNew.setOwnerSchoolName(societySchool.getSchoolName());
                            societySchoolCourseNodeOptionNew.setOwnerNodeId(societySchoolCourseNodeNew.getId());
                            societySchoolCourseNodeOptionNew.setOwnerNodeName(societySchoolCourseNodeNew.getNodeName());
                            societySchoolCourseNodeOptionNew.setOwnerQuersionId(societySchoolCourseNodeQuestionNew.getId());
                            societySchoolCourseNodeOptionNew.setOwnerQuestionName(societySchoolCourseNodeQuestionNew.getQuestionName());
                            societySchoolCourseNodeOptionNew.setOptionTitle(societySchoolCourseNodeOption.getOptionTitle());
                            societySchoolCourseNodeOptionNew.setOptionName(societySchoolCourseNodeOption.getOptionName());
                            societySchoolCourseNodeOptionNew.setIsRight(societySchoolCourseNodeOption.getIsRight());
                            societySchoolCourseNodeOptionNew.setOrderNum(societySchoolCourseNodeOption.getOrderNum());
                            societySchoolCourseNodeOptionService.insertCopy(societySchoolCourseNodeOptionNew);
                        }
                    }

                    int questionNum =
                            societySchoolCourseNodeQuestionService.countQuestionNumByNodeId(societySchoolCourseNodeNew.getId());
                    societySchoolCourseNodeNew.setQuestionNum(questionNum);
                    societySchoolCourseNodeService.updateById(societySchoolCourseNodeNew);
                }else {
                    continue;
                }

            }
            flag = true;
        }

    }
}
