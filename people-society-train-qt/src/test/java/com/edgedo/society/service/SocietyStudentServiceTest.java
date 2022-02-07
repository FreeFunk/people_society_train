package com.edgedo.society.service;

import com.edgedo.PeopleSocietyTrainQtApplication;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.HttpRequestUtil;
import com.edgedo.common.util.MD5;
import com.edgedo.common.util.RedisUtil;
import com.edgedo.face.entity.FaceMatchInfo;
import com.edgedo.face.service.IFaceAiService;
//import com.edgedo.img.service.ImgCompressService;
//import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentUnique;
import com.edgedo.society.queryvo.SocietyStudentAndNodeView;
import com.edgedo.society.queryvo.SocietyStudentView;
import com.edgedo.text.Service.ITextFenxiAiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PeopleSocietyTrainQtApplication.class)
@TestPropertySource(properties = "app.scheduling.enable=false")
@SpringBootTest
public class SocietyStudentServiceTest {
    @Autowired
    SocietyStudentService societyStudentService;
    @Autowired
    SocietyStudentUniqueService studentUniqueService;
    @Autowired
    private IFaceAiService faceAiService;
    @Autowired
    private ITextFenxiAiService textFenxiAiService;
//    @Autowired
//    private ImgCompressService imgCompressService;

    @Autowired
    private SocietyStudentAndNodeService studentAndNodeService;
    @Autowired
    private RedisUtil redisUtil;
   /* @Test
    public void clearStudentCatch() throws Exception {
        SocietyStudent stu = societyStudentService.loadById("cc2d67e460214b00ac89f30029c4f776");
        societyStudentService.clearStudentCatch(stu);
    }*/

     @Test
    public void testFaceAndIdcard() throws Exception {
         String idCardImage = FileUtil.getImageBase64Str(new File("D:\\face\\1.jpg"));
         String faceImageUrl = FileUtil.getImageBase64Str(new File("D:\\face\\2.jpg"));
         String faceToken = faceAiService.submitFaceMatch(
                 idCardImage, FaceMatchInfo.FaceTypeEnum.IDCARD,
                 faceImageUrl,FaceMatchInfo.FaceTypeEnum.LIVE);
         System.out.println("======= " + faceToken);

    }

    @Test
    public void testIdcardText() throws Exception {
        String idCardImage = FileUtil.getImageBase64Str(new File("D:\\face\\1.jpg"));
        String token = textFenxiAiService.submitIdCardFenxi(idCardImage);
        System.out.println(token);
    }



    @Test
    public void checkFaceScore() throws Exception {
        Double sc = faceAiService.queryFaceMatchScore("");
        System.out.println(sc);
    }


    @Test
    public void copyStudentToGlobleUnique() throws Exception {

//        SocietyStudent stu = societyStudentService.loadById("064fd509aeaf4034bd8c55b8cbd55f23");
        List<SocietyStudentView> listAll = societyStudentService.listAll();
        listAll.forEach((stu)->{
            SocietyStudentUnique unique = studentUniqueService.loadById(stu.getStudentIdCardNum());
            studentUniqueService.clearStudentCatch(unique);
//            studentUniqueService.insertStuUnique(stu);
        });

    }
    @Test
    public void testPwd(){
        String encodePwd = MD5.encode(MD5.encode("123456")+"211421197708285431");
        System.out.println(encodePwd);
    }

    @Test
    public void testBadiDuTokenPwd(){
        String str = (String)redisUtil.get("baidu_aip_tokens");
        System.out.println(str);
    }

    @Test
    public void testImgCompress(){

        String idCardImage = FileUtil.getImageBase64Str(new File("D:\\face\\1.jpg"));
        String ba = null;
        try {
            System.out.println(idCardImage.length()*0.75/1024);
            ba = FileUtil.compressImg(idCardImage,350);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileUtil.saveBase64Img(ba,"D:\\face",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author WangZhen
     * @Description 检查重复的学生课程节点关联
     * @Date 2020/7/18 15:30
     **/
    /*@Test
    public void  test(){
        List<SocietyStudentAndNodeView> list = studentAndNodeService.selectAAA();
        Map<String,SocietyStudentAndNodeView> map = new HashMap<>();
        for (SocietyStudentAndNodeView stuNode : list){
            String stuIdCar = stuNode.getStudentIdCardNum();
            String nodeId = stuNode.getNodeId();
            String key = stuIdCar+"_" +nodeId;
            SocietyStudentAndNodeView ora = map.get(key);
            if(ora==null){
                map.put(key,stuNode);
                continue;
            }else{
                int oraProInt = 0;
                BigDecimal Orapro = ora.getNodeProgress();
                if(Orapro!=null){
                    oraProInt = Orapro.intValue();
                }
                int newProInt = 0;
                BigDecimal newPro = stuNode.getNodeProgress();
                if(newPro!=null){
                    newProInt = newPro.intValue();
                }
                if(newProInt>oraProInt){
                    studentAndNodeService.deleteById(ora.getId());
                    map.put(key,stuNode);
                }else{
                    studentAndNodeService.deleteById(stuNode.getId());
                }

            }
        }
    }*/

   /* @Test
    public void  updateUserPwd(){
      List<SocietyStudentUnique> stuList = studentUniqueService.selectUserByCreateTime("2020-09-04");
      for(SocietyStudentUnique stu : stuList){
          SocietyStudentUnique param = new SocietyStudentUnique();
          String stuId = stu.getId();
          String encodePwd = MD5.encode(MD5.encode("123456")+stuId);
          param.setPassword(encodePwd);
          param.setId(stuId);
          studentUniqueService.update(param);
          System.out.println(stu);
      }
    }*/

}