package com.edgedo.society.controller;


import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyFileSaveRecord;
import com.edgedo.society.service.SocietyFileSaveRecordService;
import com.edgedo.tyiyunoosclient.ISysTyiyunCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/society/common")
public class CommonController extends BaseController{

	@Autowired
	private ISysTyiyunCloudStorageService sysTyiyunCloudStorageService;
	@Autowired
	private SocietyFileSaveRecordService societyFileSaveRecordService;
	@Value("${app.server.temFileForder}")
	private String temFileForder;
	@Value("${app.server.fileForderPDF}")
	private String fileForderPDF;

	/*
	* 文件上传到临时文件目录
	* */
	@RequestMapping(value = "/uploadTempFile",method = RequestMethod.POST)
	public ModelAndView uploadTempFile(MultipartFile file){
		ModelAndView modelAndView = new ModelAndView();
		String tempFilePath = "";
		try{
			//保存到临时文件目录
			tempFilePath = FileUtil.saveFile(file,temFileForder,true);
		}catch (Exception e){
			e.printStackTrace();
		}
		return buildResponse(modelAndView,temFileForder+tempFilePath);
	}

	@RequestMapping(value = "/uploadPDFFile",method = RequestMethod.POST)
	public ModelAndView uploadPDFFile(MultipartFile file,String fileType){
		ModelAndView modelAndView = new ModelAndView();
		String tempFilePath = "";
		User user = getLoginUser();
		try{
			//保存到临时文件目录
			tempFilePath = FileUtil.saveFile(file,fileForderPDF,true);
			//存入数据库中
			SocietyFileSaveRecord societyFileSaveRecord = new SocietyFileSaveRecord();
			societyFileSaveRecord.setId(Guid.guid());
			societyFileSaveRecord.setCreateTime(new Date());
			societyFileSaveRecord.setCreateUserId(user.getUserId());
			societyFileSaveRecord.setCreateUserName(user.getUserName());
			societyFileSaveRecord.setDataState("1");
			societyFileSaveRecord.setFileName(file.getOriginalFilename());
			societyFileSaveRecord.setFileType(fileType);
			societyFileSaveRecord.setFileUrl("/pdfFile"+tempFilePath);
			societyFileSaveRecord.setOwnerClsName(getFileClsName(fileType));
			societyFileSaveRecordService.insert(societyFileSaveRecord);
		}catch (Exception e){
			e.printStackTrace();
		}
		return buildResponse(modelAndView);
	}

	private String getFileClsName(String fileType) {
		if(fileType.equals("CLASS_ARCHIVES")){
			//班级档案
			return "班级档案";
		}else if (fileType.equals("STU_CHECK")){
			//学员抽查
			return "学员抽查";
		}else if (fileType.equals("STU_STUDY_ARCHIVES")){
			// 学员学习档案
			return "学员学习档案";
		}else if (fileType.equals("STUDY_TIME_PROVE")){
			//学时证明
			return "学时证明";
		}else if (fileType.equals("CLASS_CHECK")){
			//班级抽查
			return "班级抽查";
		}else {
			return "无";
		}
	}

	/*
	 * 文件上传到oos上临时文件目录
	 * */
	@RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
	public ModelAndView uploadFile(MultipartFile file) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		InputStream inputStream = file.getInputStream();
		String uuid = Guid.guid();
		String oraName = file.getOriginalFilename();
		String fileExt = oraName.substring(oraName.lastIndexOf(".") + 1).toLowerCase();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String oosFilePath =  "video/" + sdf.format(new Date())+"/"+ uuid + "." + fileExt;
		String realPath = "";
		try {
			//oos上要存的文件夹名 图片：image 视频：video
			realPath = sysTyiyunCloudStorageService.upload(oosFilePath,inputStream);
		}catch (Exception e){
			e.printStackTrace();
		}
		return buildResponse(modelAndView,realPath);
	}
}
