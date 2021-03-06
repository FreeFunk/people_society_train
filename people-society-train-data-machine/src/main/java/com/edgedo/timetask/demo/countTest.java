package com.edgedo.timetask.demo;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.FaceMatchInfo;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentAndCourse;
import com.edgedo.society.entity.SocietyStudentStudyProcessFace;
import com.edgedo.society.entity.FaceMatchInfo.FaceTypeEnum;
import com.edgedo.society.queryvo.SocietyStudentQuery;
import com.edgedo.society.queryvo.SocietyStudentView;
import com.edgedo.society.service.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

//@Component
public class countTest {
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
    @Autowired
    private SocietyStudentStudyProcessFaceService societyStudentStudyProcessFaceService;

    @Value("${app.faceai.serviceUrl}")
    private String serviceUr;

    @Autowired
    RestTemplate restTemplate;

    //    @Scheduled(cron = "0/5 * * * * ?")//?????????????????????
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
//    @Scheduled(cron = "0/5 * * * * ?")//?????????????????????
    public void test(){
        if(flag){
            flag = false;
            String[] str = {"1","2","3","4","5","6","7","8","9"};
            SocietyStudentQuery societyStudentQuery = new SocietyStudentQuery();
            List<SocietyStudentView> studentList = societyStudentService.listByObj(societyStudentQuery);
            for (SocietyStudentView societyStudentView : studentList){
                String studentIdCard = societyStudentView.getStudentIdCardNum();
                Integer ascii = stringToAscii(studentIdCard);
                Integer num1 = ascii%100;
                if (Arrays.asList(str).contains(num1.toString())){
                    societyStudentView.setStudentIdCardAscii("0"+num1.toString());
                }else {
                    societyStudentView.setStudentIdCardAscii(num1.toString());
                }

            }
            societyStudentService.updateByList(studentList);
            flag = true;
        }
    }

    public Integer stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        String[] dtr = sbu.toString().split(",");
        Integer integer = 0;
        for (String s : dtr){
            integer = integer+new Integer(s);
        }
        return integer;
    }

    //    @Scheduled(cron = "0/5 * * * * ?")//?????????????????????
    public  void count(){
        if(flag){
            flag = false;
            List<SocietyStudentStudyProcessFace> list =
                   societyStudentStudyProcessFaceService.selectByOperId("92deea89f87048bdbbcc5d756d5fd14e");
            for (SocietyStudentStudyProcessFace societyStudentStudyProcessFace : list){
                SocietyStudentAndCourse societyStudentAndCourse =
                        societyStudentAndCourseService.loadById(societyStudentStudyProcessFace.getStuCourseId());
                SocietyStudent societyStudent = societyStudentService.loadById(societyStudentAndCourse.getStudentId());
                String imgUrl = societyStudentStudyProcessFace.getFaceImageUrl();
                Double faceNum = 0.0;
                while (true){
                    faceNum = getFaceNum(imgUrl,societyStudent.getFaceImageUrl());
                    if (faceNum!=-99){
                        break;
                    }
                }
                societyStudentStudyProcessFace.setFaceScore(new BigDecimal(faceNum));
            }
            societyStudentStudyProcessFaceService.updateList(list);
            flag = true;
        }
    }

    /*??????????????????*/
    public String getFileInputStream(URL url) {
//????????????
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
//?????????????????????"GET"
            conn.setRequestMethod("GET");
//?????????????????????5???
            conn.setConnectTimeout(5 * 1000);
//?????????????????????????????????
            InputStream inStream = conn.getInputStream();
//?????????????????????????????????????????????????????????????????????????????????
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//????????????Buffer?????????
            byte[] buffer = new byte[1024];
//??????????????????????????????????????????-1???????????????????????????
            int len = 0;
//????????????????????????buffer????????????????????????
            while ((len = inStream.read(buffer)) != -1) {
//???????????????buffer???????????????????????????????????????????????????????????????len?????????????????????
                outStream.write(buffer, 0, len);
            }
//???????????????
            inStream.close();
            byte[] data = outStream.toByteArray();
//???????????????Base64??????
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(data);
            return base64;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Double getFaceNum(String imgUrl,String orgFaceUrl){
        String imgUrlStr = null;
        try {
            imgUrlStr = getFileInputStream(new URL("http://trainimg.neatedu.cn:9001"+imgUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String orgFaceUrlStr = null;
        try {
            orgFaceUrlStr = getFileInputStream(new URL("http://trainimg.neatedu.cn:9001"+orgFaceUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //1.??????????????????
        FaceMatchInfo faceMatchInfo = new FaceMatchInfo();
        String token = Guid.guid();
        faceMatchInfo.setId(token);
        faceMatchInfo.setFace1(imgUrlStr);
        faceMatchInfo.setFace1Type(FaceTypeEnum.LIVE + "");
        faceMatchInfo.setFace2(orgFaceUrlStr);
        faceMatchInfo.setFace2Type(FaceTypeEnum.LIVE + "");
        String reportDataUrl = serviceUr+"/face/acceptFaceMatch.jsn";
        String msg = (String)restTemplate.postForObject(reportDataUrl, faceMatchInfo, String.class, new Object[0]);
        if (msg != null && !msg.equals("success")) {
            return 0.0;
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FaceMatchInfo faceMatchInfo1 = new FaceMatchInfo();
            faceMatchInfo1.setId(token);
            String url = serviceUr + "/face/queryFaceMatchScore.jsn";
            Double faceNum = (Double)restTemplate.postForObject(url, faceMatchInfo1, Double.class, new Object[0]);
            return faceNum;
        }
    }

}
