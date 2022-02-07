package com.edgedo.timetask.demo;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.entity.SocietyStudentAndCourse;
import com.edgedo.society.entity.SocietyStudentCertificate;
import com.edgedo.society.service.SocietySchoolService;
import com.edgedo.society.service.SocietyStudentAndCourseService;
import com.edgedo.society.service.SocietyStudentCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 统计对已学完课程 发放证书
 */
//@Component
public class countStudentCertificate {

    @Autowired
    private SocietyStudentAndCourseService societyStudentAndCourseService;

    @Autowired
    private SocietyStudentCertificateService societyStudentCertificateService;
    @Autowired
    private SocietySchoolService societySchoolService;

    boolean flag = true;
//    @Scheduled(cron = "0 0,30 * * * ?")//半小时执行一次
    public void countStudentCer(){
        if(flag){
            flag = false;
            //1.根据证书表已有证书的学生进行筛选
            List<SocietyStudentAndCourse> notCerStulist = societyStudentAndCourseService.countIsNotCertifi();
            if(notCerStulist.size()!=0){//存在学员没有证书
                //2.获取没有被下发证书的学员
                for (SocietyStudentAndCourse societyStudentAndCourse : notCerStulist){
                    SocietySchool societySchool =
                            societySchoolService.loadById(societyStudentAndCourse.getOwnerSchoolId());
                    SocietyStudentCertificate societyStudentCertificate = new SocietyStudentCertificate();
                    societyStudentCertificate.setCreateTime(new Date());//创建时间
                    societyStudentCertificate.setOwnerSchoolId(societyStudentAndCourse.getOwnerSchoolId());//学校Id
                    societyStudentCertificate.setOwnerSchoolName(societySchool.getSchoolName());//学校名字
                    societyStudentCertificate.setOwnerMajorId(societyStudentAndCourse.getOwnerMajorId());//所属专业id
                    societyStudentCertificate.setOwnerMajorName(societyStudentAndCourse.getOwnerMajorName());//所属专业名
                    societyStudentCertificate.setClassId(societyStudentAndCourse.getClassId());//班级id
                    societyStudentCertificate.setClassName(societyStudentAndCourse.getClassName());//班级名字
                    societyStudentCertificate.setCourseId(societyStudentAndCourse.getCourseId());//课程id
                    societyStudentCertificate.setCourseName(societyStudentAndCourse.getCourseName());//课程名字
                    societyStudentCertificate.setStudentId(societyStudentAndCourse.getStudentId());//学员id
                    societyStudentCertificate.setStudentName(societyStudentAndCourse.getStudentName());//学员名字
                    societyStudentCertificate.setStudentIdCardNum(societyStudentAndCourse.getStudentIdCardNum());//身份证号
                    societyStudentCertificate.setClassAndStudentId(societyStudentAndCourse.getId());//关联学习记录ID
                    societyStudentCertificate.setCertificateCode(Guid.guid());//证书编号
                    societyStudentCertificate.setCertificateName(societyStudentAndCourse.getCourseName()+"课程合格证书");//证书名称
                    societyStudentCertificate.setCertificateImageUrl(societyStudentAndCourse.getCourseImage());//证书图片
                    societyStudentCertificate.setCertificateTime(new Date());//发证时间
                    societyStudentCertificate.setDataState("1");//数据状态
                    //3.进行插入
                    societyStudentCertificateService.insert(societyStudentCertificate);
                }
            }
            flag = true;
        }
    }


}
