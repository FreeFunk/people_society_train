package com.edgedo.timetask.demo;

import com.edgedo.wxtxmsgclient.ISysWxTxMsgClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
//@Component
public class test {
    @Autowired
    private ISysWxTxMsgClientService iSysWxTxMsgClientService;

    //@Scheduled(cron = "0 * * * * ?")
    public void test() throws Exception {

        String modelId = "SH_REMIND_MSG";
        Map<String,String> modelParam = new HashMap<>();
        modelParam.put("TITLE","实名认证审核提醒");//实名认证审核提醒
        modelParam.put("SH_STATE","已通过");//已通过
        modelParam.put("SH_TIME","2020-06-18");//2020-06-18
        modelParam.put("FAIL_REASON","照片模糊");//照片模糊
        String openId = "ovtonw2ZhVTwsHPE-e-jfjtHwtIs";//ovtonw2ZhVTwsHPE-e-jfjtHwtIs
        String clickToUrl = "";

        iSysWxTxMsgClientService.sendWxTxMsg(modelId,modelParam,clickToUrl,openId);

    }
}
