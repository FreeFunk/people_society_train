package com.edgedo.timetask.demo;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolCourseNodeMapper;
import com.edgedo.society.mapper.SocietyStudentAndCourseMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeView;
import com.edgedo.society.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

//@Component
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

    boolean flag = true;
//    @Scheduled(cron = "0/5 * * * * ?")
    public void test(){
        if(flag) {
            flag = false;
            String majorId = "dfd8a0106b9343178d00f06e63aacf17";
            String classId = "5c3379fccd99465b89b26d5ceaf82eba";
            String schoolId = "ntzxjyxt";
            String courseId = "ca41786ef89d4929a5d90bd69d22f73a";

            SocietySchoolMajor societySchoolMajor = societySchoolMajorService.selectByMajorId(majorId);
            SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
            SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
            SocietySchool societySchool = societySchoolService.loadById(schoolId);
            List<SocietySchoolClassAndStudent> list =
                    societySchoolClassAndStudentService.selectBySchoolIdVoClassId(classId,schoolId);
            for(SocietySchoolClassAndStudent societySchoolClassAndStudent : list){
                SocietyStudent societyStudent = societyStudentService.loadById(societySchoolClassAndStudent.getStudentId());
                SocietyStudentAndCourse societyStudentAndCourse = new SocietyStudentAndCourse();
                societyStudentAndCourse.setId(Guid.guid());
                societyStudentAndCourse.setOwnerSchoolId(schoolId);
                societyStudentAndCourse.setClassId(classId);
                societyStudentAndCourse.setStudentId(societyStudent.getId());
                societyStudentAndCourse.setStudentName(societyStudent.getStudentName());
                societyStudentAndCourse.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
                societyStudentAndCourse.setCourseId(societySchoolCourse.getId());
                societyStudentAndCourse.setCourseName(societySchoolCourse.getCourseName());
                societyStudentAndCourse.setCourseImage(societySchoolCourse.getCourseImage());
                societyStudentAndCourse.setCourseProgress(new BigDecimal(0));
                societyStudentAndCourse.setLearnIsFinished("0");
                societyStudentAndCourse.setFinishNodeNum(0);
                societyStudentAndCourse.setDataState("1");
                societyStudentAndCourse.setTotalLessons(societySchoolCourse.getTotalLessons());
                societyStudentAndCourse.setCoursePrice(societySchoolCourse.getCoursePrice());
                societyStudentAndCourse.setCourseOrgPrice(societySchoolCourse.getCourseOrgPrice());
                societyStudentAndCourse.setPayState("1");
                societyStudentAndCourse.setOwnerMajorId(societySchoolClass.getOwnerMajorId());
                societyStudentAndCourse.setOwnerMajorName(societySchoolClass.getOwnerMajorName());
                societyStudentAndCourse.setClassName(societySchoolClass.getClassName());
                societyStudentAndCourseMapper.insert(societyStudentAndCourse);
                SocietyStudentAndNode studentAndNode = new SocietyStudentAndNode();
                studentAndNode.setOwnerStudentAndCourseId(societyStudentAndCourse.getId());
                studentAndNode.setOwnerSchoolId(societyStudentAndCourse.getOwnerSchoolId());
                studentAndNode.setOwnerSchoolName(societySchool.getSchoolName());
                studentAndNode.setStudentId(societyStudentAndCourse.getStudentId());
                studentAndNode.setStudentName(societyStudentAndCourse.getStudentName());
                studentAndNode.setStudentIdCardNum(societyStudentAndCourse.getStudentIdCardNum());
                studentAndNode.setOwnerCourseId(societyStudentAndCourse.getCourseId());
                studentAndNode.setOwnerCourseName(societyStudentAndCourse.getCourseName());
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
                }
            }
            flag = true;
        }

    }
//    @Scheduled(cron = "0/5 * * * * ?")
    public void test1(){
        //查出所有的课程
        if(flag){
            flag = false;
            List<SocietySchoolCourse> courseList = societySchoolCourseService.selectAll();
            for(SocietySchoolCourse societySchoolCourse : courseList){
                List<SocietySchoolClass> classList = societySchoolClassService.selectByMajorId(societySchoolCourse.getOwnerMajorId());
                for(SocietySchoolClass societySchoolClass : classList){
                    if(societySchoolClass.getClassPersonNum()!=0){
                        societyStudentAndCourseMapper.updateVoClassIdAndCourseId(
                                societySchoolClass.getClassName(),societySchoolCourse.getOwnerMajorId(),societySchoolCourse.getOwnerMajorName()
                                ,societySchoolClass.getId(),societySchoolCourse.getId());
                    }else {
                        continue;
                    }
                }
            }
            flag = true;
        }

    }
}
