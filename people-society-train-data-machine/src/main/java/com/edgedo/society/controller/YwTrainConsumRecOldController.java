package com.edgedo.society.controller;


import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.ObjectUtil;
import com.edgedo.society.entity.YwTrainConsumRecOld;
import com.edgedo.society.queryvo.YwTrainConsumRecOldQuery;
import com.edgedo.society.service.YwTrainConsumRecOldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "YwTrainConsumRecOld")
@Controller
@RequestMapping("/society/ywTrainConsumRecOld")
public class YwTrainConsumRecOldController extends BaseController{
	
	@Autowired
	private YwTrainConsumRecOldService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询YwTrainConsumRecOld", notes = "分页查询YwTrainConsumRecOld")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute YwTrainConsumRecOldQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改YwTrainConsumRecOld", notes = "新增修改YwTrainConsumRecOld")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(YwTrainConsumRecOld voObj){
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
	@ApiOperation(value = "根据ID批量删除YwTrainConsumRecOld", notes = "根据ID批量删除YwTrainConsumRecOld")
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
	@ApiOperation(value = "根据ID加载YwTrainConsumRecOld", notes = "根据ID加载YwTrainConsumRecOld")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * @Author ZhangCC
	 * @Description 导入欠费记录
	 * @Date 2020/6/4 8:59
	 **/
	@RequestMapping(value = "/excelImportRec",method = RequestMethod.POST)
	public ModelAndView excelImportOweCardRec(MultipartFile file){
		ModelAndView modelAndView = new ModelAndView();
		try {
			//解析excel
			String fileName = file.getOriginalFilename();
			String fileExtend = fileName.substring(fileName.lastIndexOf(".")+1);
			if(!fileExtend.equals("xls")){
				return buildErrorResponse(modelAndView,"请上传.xls格式的文件。");
			}
			InputStream inputStream =null;
			Workbook book = null;
			Sheet carSheet = null;
			inputStream = file.getInputStream();
			if(inputStream.available()==0){
				return buildErrorResponse(modelAndView,"导入文件中没有任何内容。");
			}
			book = Workbook.getWorkbook(inputStream);
			carSheet = book.getSheet(0);
			int rows = carSheet.getRows();
			int columns = carSheet.getColumns();
			if(rows<2){
				return buildErrorResponse(modelAndView,"请填写要导入的学习记录。");
			}
			List<String> propertyEg = new ArrayList<String>();
			Cell[] headers = carSheet.getRow(0);
			for(int i=0;i<headers.length;i++){
				Cell cell = headers[i];
				String cellValue = cell.getContents();
				if(cellValue==null && cellValue.length()==0){
					propertyEg.add("none");
					continue;
				}
				propertyEg.add(fenxiHeader(cellValue));
			}
			List<YwTrainConsumRecOld> recOldList = new ArrayList<YwTrainConsumRecOld>();
			for(int i=1;i<rows;i++){
				String oweDateStr = "";
				Map<String,String> propertyMap= new HashMap<String,String>();
				Cell[] properties = carSheet.getRow(i);
				if(properties.length!=0){
					for(int j=0;j<properties.length;j++){
						Cell cell = properties[j];
						String key = propertyEg.get(j);
						if(key != null && key.equals("oweDate")){
							String content = cell.getContents();
							oweDateStr = content+":00";
						}
						propertyMap.put(key,cell.getContents());
					}
					YwTrainConsumRecOld recOld = ObjectUtil.mapToBean(propertyMap,YwTrainConsumRecOld.class);
					recOld.setId(Guid.guid());
					recOld.setDataState("1");
					recOldList.add(recOld);
				}
			}
			//批量导入欠费记录信息
			service.excelImportRec(recOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buildResponse(modelAndView);
	}

	/**
	 * excel头部区域
	 * @param cellValue
	 * @return
	 */
	private String fenxiHeader(String cellValue){
		if(cellValue.indexOf("姓名")>=0)return "studentName";
		else if(cellValue.indexOf("身份证号码")>=0)return "studentIdCard";
		else if(cellValue.equals("联系电话"))return "studnetPhoneNum";
		else if(cellValue.indexOf("培训结束时间")>=0)return "trainEndTime";
		else if(cellValue.indexOf("课时数")>=0)return "trainLessionNum";
		else if(cellValue.indexOf("是否签免费培训协议")>=0)return "isSignAgreement";
		else if(cellValue.indexOf("职业资格证书号")>=0)return "studentLicenceCode";
		else if(cellValue.indexOf("培训补贴金额")>=0)return "trainAllowBill";
		else if(cellValue.indexOf("培训专业")>=0)return "trainMajor";
		else if(cellValue.indexOf("期次")>=0)return "period";
		else if(cellValue.indexOf("就业形式")>=0)return "workType";
		else if(cellValue.indexOf("劳动合同起始时间")>=0)return "workAgreeStartTime";
		else if(cellValue.indexOf("劳动合同终止时间")>=0)return "workAgreeEndTime";
		else if(cellValue.indexOf("营业执照编号")>=0)return "certNum";
		else if(cellValue.indexOf("营业执照登记时间")>=0)return "certReginTime";
		else if(cellValue.indexOf("组织机构代码")>=0)return "organCode";
		else if(cellValue.indexOf("就业单位名称")>=0)return "compName";
		else if(cellValue.equals("就业单位联系电话"))return "compPhoneNum";
		return "none;";
	}
	
	
}
