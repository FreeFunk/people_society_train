package com.edgedo.society.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.DateUtil;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolBanner;
import com.edgedo.society.queryvo.SocietySchoolBannerQuery;
import com.edgedo.society.service.SocietySchoolBannerService;
import com.edgedo.society.service.SocietySchoolService;
import com.edgedo.tyiyunoosclient.ISysTyiyunCloudStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietySchoolBanner")
@Controller
@RequestMapping("/society/societySchoolBanner")
public class SocietySchoolBannerController extends BaseController{
	
	@Autowired
	private SocietySchoolBannerService service;

	@Autowired
	private ISysTyiyunCloudStorageService sysTyiyunCloudStorageService;

	@Autowired
	private SocietySchoolService societySchoolService;

	@Value("${app.server.fileForder}")
	private String fileForder;


	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolBanner", notes = "分页查询SocietySchoolBanner")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolBannerQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/schoolAdminQueryBanner",method = RequestMethod.POST)
	public ModelAndView schoolAdminQueryBanner(@ModelAttribute SocietySchoolBannerQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//获取当前用户id
		User user = getLoginUser();
		Map<String,String> map = societySchoolService.selectBySchoolAdminId(user.getUserId());
		query.getQueryObj().setOwnerSchoolId(map.get("ID"));
		service.schoolAdminQueryBannerlistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolBanner", notes = "新增修改SocietySchoolBanner")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolBanner voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			errMsg = service.insert(voObj);
		}else{
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除SocietySchoolBanner", notes = "根据ID批量删除SocietySchoolBanner")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.deleteByIds(list);		
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载SocietySchoolBanner", notes = "根据ID加载SocietySchoolBanner")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 学校轮播图添加
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/schoolSaveOrUpdate",method = RequestMethod.POST)
	public ModelAndView  schoolSaveOrUpdate(SocietySchoolBanner voObj){
		ModelAndView modelAndView = new ModelAndView();
		//获取当前学校管理员信息对象
		User user = getLoginUser();
		String compId = user.getCompId();
		voObj.setCreateUserId(user.getUserId());//用户人id
		voObj.setCreateTime(new Date());//创建时间
		voObj.setCreateUserName(user.getUserName());//用户人姓名
		String schoolLogoUrl= voObj.getImageUrl();
		if(schoolLogoUrl !=null && !schoolLogoUrl.equals("")){
			String filePath = "";
			String rpath = "/"+compId+"/banner";
			File file = new File(schoolLogoUrl);
			try {
				filePath = rpath+FileUtil.saveFile(file,fileForder+rpath,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setImageUrl(filePath);
		}else {
			voObj.setImageUrl(null);
		}
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			voObj.setOwnerSchoolId(user.getCompId());//学校主键
			voObj.setOwnerSchoolName(user.getUserName());//学校名字
			voObj.setShState("1");
			voObj.setIsEnable("1");
			voObj.setDataState("1");
			errMsg = service.insert(voObj);
		}else{
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}


	/**
	 * 轮播图记录修改
	 * @param societySchoolBanner
	 * @return
	 */
	@RequestMapping(value = "/updateByBannerImg",method = RequestMethod.POST)
	public ModelAndView  updateByBannerImg(SocietySchoolBanner societySchoolBanner){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String compId = user.getCompId();
		//获取当前学校管理员信息对象
		String schoolLogoUrl= societySchoolBanner.getImageUrl();
		if(schoolLogoUrl !=null && !schoolLogoUrl.equals("")){
			String filePath = "";
			String rpath = "/"+compId+"/banner";
			File file = new File(schoolLogoUrl);
			try {
				filePath = rpath+FileUtil.saveFile(file,fileForder+rpath,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			societySchoolBanner.setImageUrl(filePath);
		}
		//上传数据库
		service.updateByBannerImg(societySchoolBanner);
		return buildResponse(modelAndView);
	}

    /**
     * 切换轮播图的启用状态
     * @param id isEnable
     * @return
     */
    @RequestMapping(value = "/switchEnableState",method = RequestMethod.POST)
    public ModelAndView  switchEnableState(String id, String isEnable){
        ModelAndView modelAndView = new ModelAndView();
        service.updateEnableState(id,isEnable);
	    return buildResponse(modelAndView);
    }


	
}
