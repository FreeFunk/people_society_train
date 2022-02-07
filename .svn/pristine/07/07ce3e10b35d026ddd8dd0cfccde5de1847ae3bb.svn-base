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
//    @Scheduled(cron = "0/5 * * * * ?")//半小时执行一次
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

    //    @Scheduled(cron = "0/5 * * * * ?")//半小时执行一次
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

    /*读取网络文件*/
    public String getFileInputStream(URL url) {
//打开链接
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
//设置请求方式为"GET"
            conn.setRequestMethod("GET");
//超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
//通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
//得到图片的二进制数据，以二进制封装得到数据，具有通用性
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//创建一个Buffer字符串
            byte[] buffer = new byte[1024];
//每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
//使用一个输入流从buffer里把数据读取出来
            while ((len = inStream.read(buffer)) != -1) {
//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
//关闭输入流
            inStream.close();
            byte[] data = outStream.toByteArray();
//对字节数组Base64编码
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
        //1.提交人脸检测
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
