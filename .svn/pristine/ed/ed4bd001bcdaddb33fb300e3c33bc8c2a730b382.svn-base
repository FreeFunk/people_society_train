package com.edgedo.timetask.demo;

import com.edgedo.society.mapper.SocietyWxMessageRecMapper;
import com.edgedo.society.queryvo.SocietyWxMessageRecView;
import com.edgedo.wxtxmsgclient.ISysWxTxMsgClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*@Component*/
public class wxSendScheduledTasks {

    @Autowired
    private SocietyWxMessageRecMapper societyWxMessageRecMapper;

    @Autowired
    private ISysWxTxMsgClientService iSysWxTxMsgClientService;

    //1.定义一个标识flag 判断每一次进来方法体是否在运行
    boolean flag = true;

    //@Scheduled(cron = "0,30 * * * * ?")//30秒执行一次
    public void wxSendTask() throws Exception{
        if (flag){
            flag = false;
            //2.查出所有未发送的记录 0
            List<SocietyWxMessageRecView> list = societyWxMessageRecMapper.selectAllRec();
            //3.遍历所有list
            for(SocietyWxMessageRecView societyWxMessageRecView : list){
                //4.调用接口
                String modelId = "SH_REMIND_MSG";
                Map<String,String> modelParam = new HashMap<>();
                modelParam.put("TITLE",societyWxMessageRecView.getMessageTitle());
                modelParam.put("SH_STATE","已通过");
                modelParam.put("SH_TIME",getDate(new Date()));
                modelParam.put("FAIL_REASON",societyWxMessageRecView.getMessageText());
                String openId = societyWxMessageRecView.getStudentOpenId();
                String clickToUrl = "";

                iSysWxTxMsgClientService.sendWxTxMsg(modelId,modelParam,clickToUrl,openId);
                //5.发送一个 更新 发送记录 和 发送时间
                societyWxMessageRecMapper.updateOnId(societyWxMessageRecView.getId(),new Date());
            }
            //6.修改标识
            flag = true;
        }
    }

    private String getDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }


}
