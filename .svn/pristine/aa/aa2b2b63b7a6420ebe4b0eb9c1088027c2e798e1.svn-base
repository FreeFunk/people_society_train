//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.edgedo.common.base;

import com.edgedo.common.constant.ThirdPartyReqKey;
import com.edgedo.common.constant.ThirdPartyType;
import com.edgedo.common.shiro.IUserRealName;
import com.edgedo.common.shiro.ShiroFilter;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.FreemarkerUtil;
import com.edgedo.common.util.ObjectUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentUnique;
import com.edgedo.society.exception.StudentNotBindException;
import com.edgedo.society.exception.StudentNotRealnameException;
import com.edgedo.society.queryvo.SocietySchoolView;
import com.edgedo.society.service.SocietySchoolService;
import com.edgedo.society.service.SocietyStudentService;
import com.edgedo.society.service.SocietyStudentUniqueService;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {
    public BaseController() {
    }

    public boolean validatePhotoCode(String code) {
        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Object obj = session.getAttribute("captchaToken");
        if (code != null && obj != null && ((String)obj).equals(code.trim().toLowerCase())) {
            session.removeAttribute("captchaToken");
            return true;
        } else {
            return false;
        }
    }

    public User getLoginUser() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return (User)request.getAttribute("loginUser");
    }

    public String getWxLoginOpenId() {
        return this.getThirdPartyLoginOpenId();
    }

    public String getThirdPartyLoginOpenId() {
        Map<String, Object> infoMap = this.getThirdPartyLoginInfoMap();
        return (String)infoMap.get(ThirdPartyReqKey.third_party_login_open_id + "");
    }

    public ThirdPartyType getThirdPartyLoginType() {
        Map<String, Object> infoMap = this.getThirdPartyLoginInfoMap();
        String third_party_login_type = (String)infoMap.get(ThirdPartyReqKey.third_party_login_type + "");
        if (third_party_login_type == null) {
            return null;
        } else {
            try {
                return ThirdPartyType.valueOf(third_party_login_type);
            } catch (Exception var4) {
                return null;
            }
        }
    }

    public IUserRealName getThirdPartyLoginRealUser() {
        Map<String, Object> infoMap = this.getThirdPartyLoginInfoMap();
        return (IUserRealName)infoMap.get(ThirdPartyReqKey.third_party_login_real_user + "");
    }

    public Map<String, Object> getThirdPartyLoginInfoMap() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> wxInfoMap = (Map)request.getAttribute(ThirdPartyReqKey.third_party_login_info_map + "");
        if (wxInfoMap == null) {
            wxInfoMap = new HashMap();
        }

        return (Map)wxInfoMap;
    }

    /**
     * @Author WangZhen
     * @Description 获得登录的token
     * @Date 2020/5/5 11:48
     **/
    public String getAccessToken() {
//        return "123";
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
       return ShiroFilter.getAccessToken(request);
    }




    /**
     * @Author WangZhen
     * @Description 本项目中独有的获得当前登录学生的方法
     * 忽略实名认证
     * @Date 2020/5/4 8:44
     **/
    public SocietyStudentUnique getLoginStudentIgnoreRealName(SocietyStudentUniqueService service) {
     /*   SocietyStudent student = new SocietyStudent();
        student.setId("379f8d19ce1d497fadb9e034bf5962df");
        student.setStudentName("张春潮");
        student.setIsRealNameAuth("1");
        student.setOwnerSchoolId("JCZYJSXY");
        student.setFaceImageUrl("/2020/05/05/432686323d9942359f523807bc74b87f.jpg");
        return student;*/
        SocietyStudentUnique student = null;
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        Map<String, Object> infoMap = (Map)request.getAttribute(ThirdPartyReqKey.third_party_login_info_map + "");
        //先拿学校 从请求中拿学校
        String schoolId =  getSchoolId(request);
        if(isWx()) {
            //然后从域 中拿登录类型
            String third_party_login_type = (String)infoMap.get(ThirdPartyReqKey.third_party_login_type+"");
            ThirdPartyType thirdPartyType = ThirdPartyType.valueOf(third_party_login_type);
            //然后从狱中拿openId
            String openId = (String)infoMap.get(ThirdPartyReqKey.third_party_login_open_id + "");
            if(openId==null){
                StudentNotBindException notLogin =   new StudentNotBindException();
                notLogin.setErrCode("not_login");
                throw notLogin;
            }
            //然后根据类型，openid、学校拿学生
            if(schoolId!=null && third_party_login_type!=null){
                student = service.selectByOpenIdAndTypeSimple(thirdPartyType,openId);
            }
        }else {
            String accessToken = ShiroFilter.getAccessToken(request);
            //根据token查询
            student = service.selectByAccessTokenSimple(accessToken);
        }
        if(student==null){
            throw new StudentNotBindException();
        }

        return student;

    }

    public SocietyStudentUnique getLoginStudentIgnoreRnWhole(SocietyStudentUniqueService service) {
     /*   SocietyStudent student = new SocietyStudent();
        student.setId("379f8d19ce1d497fadb9e034bf5962df");
        student.setStudentName("张春潮");
        student.setIsRealNameAuth("1");
        student.setOwnerSchoolId("JCZYJSXY");
        student.setFaceImageUrl("/2020/05/05/432686323d9942359f523807bc74b87f.jpg");
        return student;*/
        SocietyStudentUnique student = null;
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        Map<String, Object> infoMap = (Map)request.getAttribute(ThirdPartyReqKey.third_party_login_info_map + "");
        //先拿学校 从请求中拿学校
        String schoolId =  getSchoolId(request);
        if(isWx()) {
            //然后从域 中拿登录类型
            String third_party_login_type = (String)infoMap.get(ThirdPartyReqKey.third_party_login_type+"");
            ThirdPartyType thirdPartyType = ThirdPartyType.valueOf(third_party_login_type);
            //然后从狱中拿openId
            String openId = (String)infoMap.get(ThirdPartyReqKey.third_party_login_open_id + "");
            if(openId==null){
                StudentNotBindException notLogin =   new StudentNotBindException();
                notLogin.setErrCode("not_login");
                throw notLogin;
            }
            //然后根据类型，openid、学校拿学生
            if(schoolId!=null && third_party_login_type!=null){
                student = service.selectByOpenIdAndType(thirdPartyType,openId);
            }
        }else {
            String accessToken = ShiroFilter.getAccessToken(request);
            //根据token查询
            student = service.selectByAccessToken(accessToken);
        }
        if(student==null){
            throw new StudentNotBindException();
        }

        return student;

    }

    /**
     * @Author WangZhen
     * @Description 获得当前登录的学生，
     * 如果没有实名认证就抛出异常前台会自动跳转到实名认证页面
     * @Date 2020/5/4 8:44
     **/
    public SocietyStudentUnique getLoginStudent(SocietyStudentUniqueService service) {

        SocietyStudentUnique student = getLoginStudentIgnoreRealName(service);
        String state = student.getIsRealNameAuth();
        if(state!=null && state.equals("1")){
            return student;
        }else{
            throw new StudentNotRealnameException();
        }
    }

    /**
     * @Author WangZhen
     * @Description 从参数或者header中获得学校id
     * @Date 2020/5/9 18:56
     **/
    public String getSchoolId(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return getSchoolId(request);
    }
    /**
     * @Author WangZhen
     * @Description 获得学校信息
     * @Date 2020/5/9 19:58
     **/
    public SocietySchoolView getSchoolInfo(SocietySchoolService societySchoolService){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String schId =  getSchoolId(request);
        SocietySchoolView school = null;
        if(schId!=null && schId.length()>0 && !schId.equals("null")){
            school = societySchoolService.selectSimpleSchoolById(schId);
        }
        if(school==null){
            school = societySchoolService.selectSimpleDefaultSchool();
        }

        return school;
    }

    /**
     * @Author WangZhen
     * @Description 从参数或者header中获得学校id
     * @Date 2020/5/9 18:56
     **/
    public String getSchoolId(HttpServletRequest request){
        String schoolId = request.getHeader("sys-sch");
        if(schoolId==null){
            schoolId = request.getParameter("sch");
        }
        /*if(schoolId==null){
            throw new BusinessException("no school in req param!");
        }*/
        return schoolId;
    }

    //判断是否是微信
    public boolean isWx(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Object obj = request.getAttribute("isWx");
        if(obj!=null && obj.toString().equals("1")){
            return true;
        }else{
            return false;
        }
    }

    public void outFileToBrowser(String filePath, String fileName) {
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            String extend = FileUtil.getFileExtend(filePath);
            fileName = fileName + "." + extend;
            File temFile = new File(filePath);
            if (temFile.exists()) {
                String browser = request.getHeader("user-agent");
                Pattern p = Pattern.compile(".*MSIE.*?;.*");
                Matcher m = p.matcher(browser);
                if (m.matches()) {
                    response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replace("+", ""));
                } else {
                    response.setHeader("Content-Disposition", "attachment; filename=" + (new String(fileName.getBytes("UTF-8"), "ISO8859-1")).replace(" ", ""));
                }

                response.setContentType("application/x-download");
                bis = new BufferedInputStream(new FileInputStream(temFile));
                bos = new BufferedOutputStream(response.getOutputStream());
                IOUtils.copy(bis, bos);
                return;
            }

            response.getWriter().write("ERROR:File Not Found");
        } catch (Exception var26) {
            var26.printStackTrace();
            return;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException var25) {
                    var25.printStackTrace();
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException var24) {
                    var24.printStackTrace();
                }
            }

        }

    }

    public void outInputStreamImgToBrowser(InputStream is) {
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(response.getOutputStream());
            IOUtils.copy(bis, bos);
        } catch (Exception var19) {
            var19.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }
            }

        }

    }

    public void exportExcelToBrowser(String fileName, String[] keys, String[] headers, List list) {
        Map<String, Object> data = new HashMap();
        data.put("keys", keys);
        data.put("headers", headers);
        data.put("list", list);
        fileName = fileName + ".xls";
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String tplForder = request.getServletContext().getRealPath("/WEB-INF/export/tpl");
        String tplPath = tplForder + File.separator + "common_xls_template.ftl";
        ServletOutputStream os = null;

        try {
            String browser = request.getHeader("user-agent");
            Pattern p = Pattern.compile(".*MSIE.*?;.*");
            Matcher m = p.matcher(browser);
            if (m.matches()) {
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replace("+", ""));
            } else {
                response.setHeader("Content-Disposition", "attachment; filename=" + (new String(fileName.getBytes("UTF-8"), "ISO8859-1")).replace(" ", ""));
            }

            response.setContentType("application/x-download");
            os = response.getOutputStream();
            FreemarkerUtil.framemarkerGen(tplPath, data, os);
        } catch (Exception var22) {
            var22.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException var21) {
                    var21.printStackTrace();
                }
            }

        }

    }

    public void exportOfTemplate(String templateName, String fileName, Map data) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        File teamReportFile = new File("/server/app/bigdata-web/bin/exceltemplate/" + templateName);
        String tplPath = teamReportFile.getPath();
        ServletOutputStream os = null;

        try {
            String browser = request.getHeader("user-agent");
            Pattern p = Pattern.compile(".*MSIE.*?;.*");
            Matcher m = p.matcher(browser);
            if (m.matches()) {
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replace("+", ""));
            } else {
                response.setHeader("Content-Disposition", "attachment; filename=" + (new String(fileName.getBytes("UTF-8"), "ISO8859-1")).replace(" ", ""));
            }

            response.setContentType("application/x-download");
            os = response.getOutputStream();
            FreemarkerUtil.framemarkerGen(tplPath, data, os);
        } catch (Exception var20) {
            var20.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                }
            }

        }

    }

    public String getIpAddr() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("PRoxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public String getUserAgent() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String userAgent = request.getHeader("user-agent");
        return userAgent;
    }

    public String getDiviceType() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Object obj = request.getParameter("DIVICETYPE");
        return obj != null ? obj.toString() : "PC端";
    }

    public String redirectToUrl(String url, HttpServletResponse response) {
        try {
            response.sendRedirect(url);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public void outStringToBrowser(String str) {
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        PrintWriter pw = null;

        try {
            pw = response.getWriter();
            pw.write(str);
            pw.flush();
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }

        }

    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomerDateEditor(true));
    }

    public ModelAndView buildResponse(ModelAndView model, Object data) {
        model.addObject("success", true);
        model.addObject("data", data);
        model.addObject("code", "0");
        model.addObject("modelAndViewData", "1");
        return model;
    }

    public ModelAndView buildResponse(ModelAndView model) {
        model.addObject("success", true);
        model.addObject("code", "0");
        model.addObject("modelAndViewData", "1");
        return model;
    }

    public ModelAndView buildResponse(ModelAndView model, QueryObj query) {
        model.addObject("code", "0");
        model.addObject("modelAndViewData", "1");
        Map<String, Object> mapInfo = ObjectUtil.beanToMap(query);
        model.addAllObjects(mapInfo);
        return model;
    }

    public ModelAndView buildErrorResponse(ModelAndView model, String errMsg) {
        model.addObject("code", "-1");
        model.addObject("success", false);
        model.addObject("errMsg", errMsg);
        model.addObject("modelAndViewData", "1");
        return model;
    }

    public ModelAndView buildErrorResponse(ModelAndView model, String errMsg, String errCode) {
        model.addObject("code", "-1");
        model.addObject("success", false);
        model.addObject("errMsg", errMsg);
        model.addObject("errCode", errCode);
        model.addObject("modelAndViewData", "1");
        return model;
    }

    public void outAjax1(String JsonStr) {
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();

        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(JsonStr);
            response.getWriter().flush();
        } catch (Exception var12) {
            ;
        } finally {
            try {
                response.getWriter().close();
            } catch (Exception var11) {
                var11.printStackTrace();
            }

        }

    }
}
