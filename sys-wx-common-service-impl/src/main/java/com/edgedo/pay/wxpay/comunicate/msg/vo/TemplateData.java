package com.edgedo.pay.wxpay.comunicate.msg.vo;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.common.util.IocUtil;
import com.edgedo.sys.entity.SysWxTemplateMsgconfig;
import com.edgedo.sys.service.SysWxTemplateMsgconfigService;
import jodd.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板消息数据对象
 *
 */
public class TemplateData {


    @Value("${wx.config.rootOrginalId}")
    String orginalId;

    /**发送给谁**/
    private String touser;
    /**模板ID**/
    private String template_id;
    /**消息url**/
    private String url;
    /**字体颜色**/
    private String topcolor="#000000";
    /**原始id**/
    private String orgId ;
    /**用户id**/
    private String userId;
    /**消息类型**/
    private String msgType;
    /**消息体**/
    private TemplateDataItem data = new TemplateDataItem();

    /**
     * 小程序发送功能
     */
    private String miniprogramAppid;
    /**
     * 小程序发送功能
     */
    private String miniprogramPagepath;

    /**
     * 根据消息类型取得字段名称
     * @param msgType
     * @return
     */
    public static String[] getDataNameByType(int msgType){
        String[]  dataName = {};
        String dataNames ="";
        dataName = dataNames.split(",");
        return dataName;
    }

    /**
     * 根据消息类型取得字段对应数据
     * @return
     */
    public static List<String> getDataFromJson(int msgType,String jsonstring){
        List<String> data = new ArrayList<String>();
        String keys = "";
        data = getDataFromJson(keys.split(","), jsonstring);
        return data;
    }
    /**
     * json转list
     * @param keys
     * @param json
     * @return
     */
    public static List<String> getDataFromJson(String[] keys,String json){
        List<String> result = new ArrayList<String>();
        try{
            JSONObject jsonObject = JSONObject.parseObject(json);
            Map m = (Map)jsonObject.get("data");
            if(m != null){
                for(String s:keys){
                    if((Map)m.get(s) != null){
                        result.add((String)((Map)m.get(s)).get("value"));
                    }else{
                        result.add("");
                    }
                }
            }
        }catch(Exception e){
            return result;
        }
        return result;
    }
    /**
     * 获取json格式的模板消息字符串
     * @return
     */

    /**
     * 获取json格式的模板消息字符串
     * @return
     */
    public String makeTemplateMSG(){
        String jsonMsg = "";
        if(miniprogramAppid!=null && miniprogramAppid.length()>0 && miniprogramPagepath!=null && miniprogramPagepath.length()>0){
            //有小程序跳转的消息
            jsonMsg = "{" +
                    "\"touser\":\"%s\"," +
                    "\"template_id\":\"%s\"," +
                    "\"url\":\"%s\", " +
                    "\"miniprogram\":{" +
                    "\"appid\":\"%s\"," +
                    "\"path\":\"%s\"" +
                    "}," +
                    "\"topcolor\":\"%s\"," +
                    "\"data\":%s}";
            jsonMsg = String.format(
                    jsonMsg,
                    this.touser,
                    this.template_id,
                    this.url,
                    this.miniprogramAppid,
                    this.miniprogramPagepath,
                    this.topcolor,
                    net.sf.json.JSONObject.fromObject(this.data).toString());
        }else{
            jsonMsg = "{\"touser\":\"%s\",\"template_id\":\"%s\",\"url\":\"%s\",\"topcolor\":\"%s\",\"data\":%s}";
            jsonMsg = String.format(
                    jsonMsg,
                    this.touser,
                    this.template_id,
                    this.url,
                    this.topcolor,
                    net.sf.json.JSONObject.fromObject(this.data).toString());
        }

        //.replace("\"", "\\\""));
        return jsonMsg;
    }
    /*public String makeTemplateMSG(){
        String jsonMsg = "{\"touser\":\"%s\",\"template_id\":\"%s\",\"url\":\"%s\",\"topcolor\":\"%s\",\"data\":%s}";
        jsonMsg = String.format(
                jsonMsg,
                this.touser,
                this.template_id,
                this.url,
                this.topcolor,
                JSONObject.toJSONString(this.data).toString());
        //.replace("\"", "\\\""));
        return jsonMsg;
    }*/
    /**
     * 组织data数据
     * @param keys
     * @param data
     * @return
     */
    public TemplateData transData(String[] keys,List<String> data,TemplateData td ){
        for(int i = 0;i<keys.length;i++){
            String key = keys[i];
            String value = "";
            if(data.size()-1 >= i ){
                value = data.get(i);
            }
            td.push(key, value);
        }
        return td;
    }


    /**
     * 生成模板消息信息
     * @param openId
     * @param dataList
     * @return
     */

    public static TemplateData createTemplate(
            String modelId,
            String openId,List<String> dataList,
            String temUrl,String orginalId
    ){
        TemplateData result = null;
        //根据消息类型选择
        try{
            //取得配置信息
            //取得配置信息
            SysWxTemplateMsgconfigService sysWxTemplateMsgconfigService = IocUtil.getBean(SysWxTemplateMsgconfigService.class);
            SysWxTemplateMsgconfig sysWxTemplateMsgconfig = sysWxTemplateMsgconfigService.loadById(modelId);
            String keys =sysWxTemplateMsgconfig.getDataKey(),templateid=sysWxTemplateMsgconfig.getWxTemplateId(),url=sysWxTemplateMsgconfig.getUrl();
            if(temUrl!=null && temUrl.length()>0){
                url = temUrl;
            }
            result = new TemplateData(openId,templateid,url);
            result.transData(keys.split(","), dataList, result);
            result.setMsgType(sysWxTemplateMsgconfig.getId());
            result.setOrgId(orginalId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
/*    public static TemplateData createTemplate( Object templateMsgconfig *//*PtWxTemplateMsgconfig templateMsgconfig*//*,String openId,List<String> dataList,String temUrl){
        TemplateData result = null;
        //根据消息类型选择
        *//*try{
            //取得配置信息
            PtSysConfigService sysConfigService = IocUtil.getBean(PtSysConfigService.class);
            String keys =templateMsgconfig.getDataKey(),templateid=templateMsgconfig.getWxTemplateId(),url=templateMsgconfig.getUrl();
            if(temUrl!=null && temUrl.length()>0){
                url = temUrl;
            }
            result = new TemplateData(openId,templateid,url);
            result.transData(keys.split(","), dataList, result);
            result.setMsgType(templateMsgconfig.getId());
            //设置orginalid
            String orginalId = sysConfigService.getConfig(WxPayUtil.WX_ROOT_ORGINAL_ID);
            result.setOrgId(orginalId);
        }catch(Exception e){
            e.printStackTrace();
        }*//*
        return result;
    }*/

    public TemplateData(){

    }

    public  TemplateData(String touser, String template_id, String url, String topcolor) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
        this.topcolor = topcolor;
    }

    public TemplateData(String touser, String template_id) {
        this.touser = touser;
        this.template_id = template_id;
    }

    public TemplateData(String touser, String template_id, String url) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public TemplateDataItem getData() {
        return data;
    }

    public void setData(TemplateDataItem data) {
        this.data = data;
    }

    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Deprecated
    private TemplateDataItem getTemplateDataItemInstance() {
        return getData();
    }

    public TemplateData push(String key, String value, String color) {
        this.data.addItem(key, value, color);
        return this;
    }

    public TemplateData push(String key, String value) {
        this.data.addItem(key, value);
        return this;
    }

    public class TemplateDataItem extends HashMap<String, Item> {
        private static final long serialVersionUID = 1L;
        public Item getItemInstance(String value) {
            return new Item(value);
        }

        public Item getItemInstance(String value, String color) {
            return new Item(value, color);
        }

        public TemplateDataItem() {
        }

        public TemplateDataItem addItem(String key, String value) {
            this.put(key, new Item(value));
            return this;
        }

        public TemplateDataItem addItem(String key, String value, String color) {
            this.put(key, new Item(value, color));
            return this;
        }
    }

    public class Item {
        private String value;
        private String color = "#000000";

        public Item(String value) {
            this.value = value;
        }

        public Item(String value, String color) {
            this.value = value;
            this.color = color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getColor() {
            return color;
        }

    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMiniprogramAppid() {
        return miniprogramAppid;
    }

    public void setMiniprogramAppid(String miniprogramAppid) {
        this.miniprogramAppid = miniprogramAppid;
    }

    public String getMiniprogramPagepath() {
        return miniprogramPagepath;
    }

    public void setMiniprogramPagepath(String miniprogramPagepath) {
        this.miniprogramPagepath = miniprogramPagepath;
    }
}
