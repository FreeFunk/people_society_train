package com.edgedo.timetask.demo;

import com.edgedo.common.util.Guid;
import com.edgedo.society.controller.SocietySchoolCourseNodeController;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolCourseNodeMapper;
import com.edgedo.society.mapper.SocietyStudentAndCourseMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeView;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import com.edgedo.society.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public class task {


    @Autowired
    private SocietySchoolMajorService societySchoolMajorService;
    @Autowired
    private SocietySchoolClassService societySchoolClassService;
    @Autowired
    private SocietyStudentService societyStudentService;
    @Autowired
    private SocietySchoolClassAndStudentService societySchoolClassAndStudentService;
    @Autowired
    private SocietySchoolCourseService societySchoolCourseService;
    @Autowired
    private SocietySchoolService societySchoolService;
    @Autowired
    private SocietySchoolCourseNodeMapper courseNodeMapper;
    @Autowired
    private SocietyStudentAndNodeService studentAndNodeService;
    @Autowired
    private SocietyStudentAndCourseMapper societyStudentAndCourseMapper;
    @Autowired
    private SocietySchoolCourseNodeController societySchoolCourseNodeController;
    boolean flag = true;

/*    @Scheduled(cron = "0/5 * * * * ?")
    public void test(){
        if(flag){
            flag = false;
            String majorId = "d81f0c471ad74925b3e27a1aee6ea03b";
            String classId = "af614a814138495e8c4755080ce236bc";//bbfaa21dae724f2fa932414edb945d3f 育婴
            String schoolId = "qhdshgqqczyjnpxxx";
            String courseId = "cf907fbb79e84005a893374fc7927700";
            //cf907fbb79e84005a893374fc7927700 前程 中式  36b11c568527432f97ce9ddeaf4e83ef 前程 育婴
            //ac19d6c8ea2a4ce3b016b42030b14c0d 金管家 中式  cd6402d1ce4c4fa5a64a2fe63111b861 金管家 育婴
            //111dcb9bab2540588a9fd0ae3eea0626

            SocietySchoolMajor societySchoolMajor = societySchoolMajorService.selectByMajorId(majorId);
            SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
            SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
            SocietySchool societySchool = societySchoolService.loadById(schoolId);
            List<SocietyStudentAndCourseView> list =
                    societyStudentAndCourseMapper.selectByClassId(classId);
            for(SocietyStudentAndCourseView societyStudentAndCourseView : list){
                
                *//*SocietyStudentAndNode studentAndNode = new SocietyStudentAndNode();
                studentAndNode.setOwnerStudentAndCourseId(societyStudentAndCourseView.getId());
                studentAndNode.setOwnerSchoolId(societyStudentAndCourseView.getOwnerSchoolId());
                studentAndNode.setOwnerSchoolName(societySchool.getSchoolName());
                studentAndNode.setStudentId(societyStudentAndCourseView.getStudentId());
                studentAndNode.setStudentName(societyStudentAndCourseView.getStudentName());
                studentAndNode.setStudentIdCardNum(societyStudentAndCourseView.getStudentIdCardNum());
                studentAndNode.setOwnerCourseId(societyStudentAndCourseView.getCourseId());
                studentAndNode.setOwnerCourseName(societyStudentAndCourseView.getCourseName());
                SocietySchoolCourseNodeQuery query = new SocietySchoolCourseNodeQuery();
                query.getQueryObj().setOwnerSchoolId(societySchool.getId());
                query.getQueryObj().setOwnerCourseId(societySchoolCourse.getId());
                query.getQueryObj().setShState("1");
                List<SocietySchoolCourseNodeView> courseNodeList = courseNodeMapper.listByObj(query);
                if(courseNodeList != null && courseNodeList.size() >0){
                    for(SocietySchoolCourseNodeView courseNode:courseNodeList){
                        studentAndNode.setNodeId(courseNode.getId());
                        studentAndNode.setNodeName(courseNode.getNodeName());
                        studentAndNode.setNodeProgress(new BigDecimal(0));
                        studentAndNode.setStudyTimeLength(0);
                        studentAndNode.setLastLearnLocation(0);
                        studentAndNode.setLearnIsFinished("0");
                        studentAndNode.setNodeQuestionScore(0);
                        studentAndNode.setQuestionIsFinished("0");
                        studentAndNode.setQuestionIsPass("0");
                        studentAndNodeService.insert(studentAndNode);
                    }
                }*//*
            }
            flag = true;
        }
    }*/
    @Scheduled(cron = "0/5 * * * * ?")
    public void test(){
        if (flag){
            flag = false;
            //cf907fbb79e84005a893374fc7927700 前程 中式  36b11c568527432f97ce9ddeaf4e83ef 前程 育婴
            List<SocietySchoolCourseNode> list = courseNodeMapper.selectByCourseIdAndNull("af33cd689bca44d7a7849275ad7955d9");

            for (SocietySchoolCourseNode societySchoolCourseNode : list){
                String fileId = societySchoolCourseNode.getFileId();
                String url = societySchoolCourseNodeController.getFileTranscoding(fileId);
                societySchoolCourseNode.setFileUrl(url);
                courseNodeMapper.updateById(societySchoolCourseNode);
            }
            flag = true;
        }

    }
}
