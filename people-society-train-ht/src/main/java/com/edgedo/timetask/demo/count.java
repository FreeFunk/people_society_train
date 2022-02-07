package com.edgedo.timetask.demo;

import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.SocietySchoolClassAndStudentView;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import com.edgedo.society.service.*;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author QiuTianZhu
 * @Description:
 * @Param:
 * @return:
 * @Date 2020/7/22 08:43
 **/
//@Component
public class count {
    boolean flag = true;
    @Autowired
    private SocietySchoolClassAndStudentService societySchoolClassAndStudentService;

    @Autowired
    private SocietyStudentAndCourseService societyStudentAndCourseService;
    @Autowired
    private SocietyStudentAndNodeService societyStudentAndNodeService;
    @Autowired
    private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
    @Autowired
    private SocietyStudentService societyStudentService;
    @Autowired
    private SocietySchoolClassService societySchoolClassService;
    @Autowired
    private SocietySchoolClassAndCourseService societySchoolClassAndCourseService ;


//    @Scheduled(cron = "0/5 * * * * ?")//半小时执行一次
/*    public void count(){
        if(flag){
            flag = false;
            List<SocietyStudentAndNode> list = societyStudentAndNodeService.selectFinishTime();
            for(SocietyStudentAndNode societyStudentAndNode : list){
                Date time = societyStudentPractiseQuestionService.selectByNodeId(societyStudentAndNode.getId());
                societyStudentAndNode.setFinishTime(time);
                societyStudentAndNodeService.update(societyStudentAndNode);
            }
            flag = true;
        }
    }*/
//@Scheduled(cron = "0/5 * * * * ?")//半小时执行一次
    public  void count(){
        if(flag){
            flag = false;
            List<SocietySchoolClassAndCourse> list = societySchoolClassAndCourseService.selectByAll();
            for (SocietySchoolClassAndCourse societySchoolClassAndCourse : list){
                SocietySchoolClass societySchoolClass =
                        societySchoolClassService.loadById(societySchoolClassAndCourse.getClassId());
                if(societySchoolClass==null){
                    societySchoolClassAndCourse.setDataState("0");
                    societySchoolClassAndCourseService.update(societySchoolClassAndCourse);
                }
            }
            flag = true;
        }
    }

//    @Scheduled(cron = "0/5 * * * * ?")//半小时执行一次
/*    public void count(){
        if(flag){
            flag = false;

            List<SocietySchoolClass> classList = societySchoolClassService.selectByClassList();

            for(SocietySchoolClass societySchoolClass : classList){
                List<SocietyStudentAndCourseView> list =
                        societyStudentAndCourseService.selectByClassId(societySchoolClass.getId());
                if (list.size()!=0){
                    societyStudentAndCourseService.updateByClassIdList(list,societySchoolClass);
                }

            }
            flag = true;
        }
    }*/
}
