package com.edgedo.timetask;

import com.edgedo.common.util.RedisUtil;
import com.edgedo.constant.WxCommonReidsKeyConstant;
import com.edgedo.pay.wxpay.comunicate.msg.util.SendTemplateMessageUtil;
import com.edgedo.sys.entity.SysWxTemplateMsgconfig;
import com.edgedo.sys.entity.SysWxTxMsgVo;
import com.edgedo.sys.service.SysWxTemplateMsgconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@ConditionalOnProperty(
        value = {"timetask.SendWxTxMsgTask.enable"},
        havingValue = "true",
        matchIfMissing = false
)
@Component
public class SendWxTxMsgTask {

    @Autowired
    private RedisUtil redisUtil;
    @Value("${wx.config.rootOrginalId}")
    String orginalId;
    @Autowired
    SysWxTemplateMsgconfigService sysWxTemplateMsgconfigService;

    /*
    * 发送微信提醒的定时任务
    * */
    @Scheduled(cron = "${timetask.SendWxTxMsgTask.cron.sendWxTxMsg}")
    public void sendWxTxMsg(){
        while(true){
            Object obj = redisUtil.rightPop(WxCommonReidsKeyConstant.WX_TX_MSG_KEY);
            if(obj==null){
                return;
            }
            try {
                SysWxTxMsgVo sysWxTxMsgVo = (SysWxTxMsgVo)obj;
                String openId = sysWxTxMsgVo.getOpenId();
                String modelId = sysWxTxMsgVo.getModelId();
                String modelParams = sysWxTxMsgVo.getModelParams();
                String cilckToUrl = sysWxTxMsgVo.getCilckToUrl();
                SendTemplateMessageUtil.sendTemplateMessageByModelId(openId,modelId,modelParams,cilckToUrl,orginalId);
            }catch (Exception e){
                redisUtil.leftPush(WxCommonReidsKeyConstant.WX_TX_MSG_KEY,obj);
            }
        }
    }
}


