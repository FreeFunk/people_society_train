package com.edgedo.society.controller;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.FaceMatchInfo;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentAndCourse;
import com.edgedo.society.entity.SocietyStudentStudyProcessFace;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessFaceQuery;
import com.edgedo.society.service.SocietyStudentAndCourseService;
import com.edgedo.society.service.SocietyStudentService;
import com.edgedo.society.service.SocietyStudentStudyProcessFaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;


@Api(tags = "SocietyStudentStudyProcessFace")
@Controller
@RequestMapping("/society/societyStudentStudyProcessFace")
public class SocietyStudentStudyProcessFaceController extends BaseController {

    @Autowired
    private SocietyStudentStudyProcessFaceService service;
    @Autowired
    private SocietyStudentService societyStudentService;
    @Value("${app.server.fileForder}")
    private String fileForder;
    @Value("${app.faceai.serviceUrl}")
    private String serviceUrl;

    @Value("${app.faceai.maxfacelength}")
    private Integer app_faceai_maxfacelength;
    @Value("${app.faceai.imgcompresswidth}")
    private Integer app_faceai_imgcompresswidth;
    @Value("${app.faceai.ImgPath}")
    private String ImgPath;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SocietyStudentAndCourseService societyStudentAndCourseService;

    /**
     * ????????????
     *
     * @param query
     * @return
     */
    @ApiOperation(value = "????????????SocietyStudentStudyProcessFace", notes = "????????????SocietyStudentStudyProcessFace")
    @ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
    @RequestMapping(value = "/listpage", method = RequestMethod.POST)
    public ModelAndView listpage(@ModelAttribute SocietyStudentStudyProcessFaceQuery query) {
        ModelAndView modelAndView = new ModelAndView();
        if (query.getOrderBy() == null || query.getOrderBy().equals("")) {
            query.setOrderBy("OWNER_NODE_NAME ASC");
        }
        service.listPage(query);
        buildResponse(modelAndView, query);
        return modelAndView;
    }


    @RequestMapping(value = "/listpageHuiFu", method = RequestMethod.POST)
    public ModelAndView listpageHuiFu(@ModelAttribute SocietyStudentStudyProcessFaceQuery query) {
        ModelAndView modelAndView = new ModelAndView();
        if (query.getOrderBy() == null || query.getOrderBy().equals("")) {
            query.setOrderBy("OWNER_NODE_NAME ASC");
        }
        service.listpageHuiFu(query);
        buildResponse(modelAndView, query);
        return modelAndView;
    }

    /**
     * ????????????
     *
     * @param voObj
     * @return
     */
    @ApiOperation(value = "????????????SocietyStudentStudyProcessFace", notes = "????????????SocietyStudentStudyProcessFace")
    @ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(SocietyStudentStudyProcessFace voObj) {
        ModelAndView modelAndView = new ModelAndView();
        String errMsg = "";
        String id = voObj.getId();
        if (id == null || id.trim().equals("")) {
            errMsg = service.insert(voObj);
        } else {
            errMsg = service.update(voObj);
        }
        if (errMsg != null && !errMsg.equals("")) {
            buildErrorResponse(modelAndView, errMsg);
        } else {
            buildResponse(modelAndView);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/saveOrUpdateNew", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateNew(SocietyStudentStudyProcessFace voObj) {
        ModelAndView modelAndView = new ModelAndView();
        String errMsg = "";
        User user = getLoginUser();
        voObj.setOperatorId(user.getUserId());
        voObj.setOperatorName(user.getUserName());
        voObj.setOperatorTime(new Date());
        String certImgUrl = voObj.getFaceImageUrl();
        if (certImgUrl != null && !certImgUrl.equals("")) {
            String filePath = "";
            String rpath = "/" + voObj.getOwnerSchoolId() + "/faceimg";
            File file = new File(certImgUrl);
            try {
//                filePath = rpath+FileUtil.saveFile(file,fileForder+rpath,true);
                String faceOld = FileUtil.getImageBase64Str(file);
                int base64Length = faceOld.length();
                int base64Kb = (int)(base64Length*0.75/1024);
                if(base64Kb>=app_faceai_maxfacelength){
                    faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
                }
                filePath = rpath + FileUtil.saveBase64Img(faceOld, fileForder + rpath, true);
                Double num = getFaceScoreNum(filePath,voObj.getStudentId());
                voObj.setFaceScore(new BigDecimal(num));
                //??????????????????
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            voObj.setFaceImageUrl(filePath);
        } else {
            voObj.setFaceImageUrl(null);
        }

        errMsg = service.update(voObj);

        if (errMsg != null && !errMsg.equals("")) {
            buildErrorResponse(modelAndView, errMsg);
        } else {
            buildResponse(modelAndView);
        }
        return modelAndView;
    }
    //????????????????????????????????? ???????????????
    private Double getFaceScoreNum(String faceImg,String studentId){
        SocietyStudent societyStudent = societyStudentService.loadById(studentId);
        String studentFace = societyStudent.getFaceImageUrl();
        String imgUrlStr = null;
        try {
            imgUrlStr = getFileInputStream(new URL(ImgPath+faceImg));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String orgFaceUrlStr = null;
        try {
            orgFaceUrlStr = getFileInputStream(new URL(ImgPath+studentFace));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        FaceMatchInfo faceMatchInfo = new FaceMatchInfo();
        String token = Guid.guid();
        faceMatchInfo.setId(token);
        faceMatchInfo.setFace1(imgUrlStr);
        faceMatchInfo.setFace1Type(FaceMatchInfo.FaceTypeEnum.LIVE + "");
        faceMatchInfo.setFace2(orgFaceUrlStr);
        faceMatchInfo.setFace2Type(FaceMatchInfo.FaceTypeEnum.LIVE + "");
        String reportDataUrl = serviceUrl + "/face/acceptFaceMatch.jsn";
        String msg = (String)restTemplate.postForObject(reportDataUrl, faceMatchInfo, String.class, new Object[0]);
        Double faceNum = 0.0;
        if (msg != null && !msg.equals("success")) {
            faceNum = 0.0;
        }else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FaceMatchInfo faceMatchInfo1 = new FaceMatchInfo();
            faceMatchInfo1.setId(token);
            String url = serviceUrl + "/face/queryFaceMatchScore.jsn";
            faceNum = restTemplate.postForObject(url, faceMatchInfo1, Double.class, new Object[0]);
        }
        return faceNum;
    }

    @RequestMapping(value = "/insertNew", method = RequestMethod.POST)
    public ModelAndView insertNew(SocietyStudentStudyProcessFace voObj) {
        ModelAndView modelAndView = new ModelAndView();
        String errMsg = "";
        User user = getLoginUser();
        voObj.setOperatorId(user.getUserId());
        voObj.setOperatorName(user.getUserName());
        voObj.setOperatorTime(new Date());
        String certImgUrl = voObj.getFaceImageUrl();
        if (certImgUrl != null && !certImgUrl.equals("")) {
            String filePath = "";
            String rpath = "/" + voObj.getOwnerSchoolId() + "/faceimg";
            File file = new File(certImgUrl);
            try {
                String faceOld = FileUtil.getImageBase64Str(file);
                int base64Length = faceOld.length();
                int base64Kb = (int)(base64Length*0.75/1024);
                if(base64Kb>=app_faceai_maxfacelength){
                    faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
                }
                filePath = rpath + FileUtil.saveBase64Img(faceOld, fileForder + rpath, true);
                Double num = getFaceScoreNum(filePath,voObj.getStudentId());
                voObj.setFaceScore(new BigDecimal(num));
                //??????????????????
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            voObj.setFaceImageUrl(filePath);

        } else {
            voObj.setFaceImageUrl(null);
        }

        errMsg = service.insert(voObj);
        if (errMsg != null && !errMsg.equals("")) {
            buildErrorResponse(modelAndView, errMsg);
        } else {
            buildResponse(modelAndView);
        }
        return modelAndView;
    }


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

    /**
     * ????????????
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "??????ID????????????SocietyStudentStudyProcessFace", notes = "??????ID????????????SocietyStudentStudyProcessFace")
    @ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
    public ModelAndView delete(String ids) {
        ModelAndView modelAndView = new ModelAndView();
        String[] arr = ids.split(",");
        User user = getLoginUser();
        List<String> list = new ArrayList<String>();
        for (String str : arr) {
            list.add(str);
        }
        service.deleteByIds(list, user);
        return buildResponse(modelAndView);
    }

    /**
     * ????????????
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/retrieveFace", method = RequestMethod.POST)
    public ModelAndView retrieveFace(String ids) {
        ModelAndView modelAndView = new ModelAndView();
        String[] arr = ids.split(",");
        List<String> list = new ArrayList<String>();
        for (String str : arr) {
            list.add(str);
        }
        service.retrieveFaceByIds(list);
        return buildResponse(modelAndView);
    }


    /**
     * ??????????????????
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "??????ID??????SocietyStudentStudyProcessFace", notes = "??????ID??????SocietyStudentStudyProcessFace")
    @ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
    @RequestMapping(value = "/loadById", method = RequestMethod.POST)
    public ModelAndView loadById(String id) {
        ModelAndView modelAndView = new ModelAndView();
        return buildResponse(modelAndView, service.loadById(id));
    }


}
