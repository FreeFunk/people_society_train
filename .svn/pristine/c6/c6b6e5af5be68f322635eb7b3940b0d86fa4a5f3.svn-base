package com.edgedo.society.controller;

import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.comm.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.util.FreemarkerUtil;
import com.edgedo.society.entity.TyunOjbect;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeView;
import com.edgedo.society.service.SocietySchoolCourseNodeService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/society/testPdfController")
public class TestPdfController extends BaseController {

    @Autowired
    private SocietySchoolCourseNodeService societySchoolCourseNodeService;

    @Value("${app.tcloudDomain}")
    private String tcloudDomain;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView test(@RequestParam String params){
        ModelAndView modelAndView = new ModelAndView();
        List<TyunOjbect> dataList =JSON.parseArray(params, TyunOjbect.class);
        //获取后台所有的视频
        List<String> list = societySchoolCourseNodeService.selectAll();
        for (TyunOjbect tyunOjbect : dataList){
            String fileUrlStr = tcloudDomain+"/"+tyunOjbect.getFileUrl();
            if (list.contains(fileUrlStr)){
                tyunOjbect.setDataState("1");
            }else {
                tyunOjbect.setDataState("0");
            }
            Long size = new Long(tyunOjbect.getFileSize());
            tyunOjbect.setFileSize(getNetFileSizeDescription(size));
        }
        return buildResponse(modelAndView,dataList);
    }

    public String getNetFileSizeDescription(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        }
        else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        }
        else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        }
        else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            }
            else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }
}