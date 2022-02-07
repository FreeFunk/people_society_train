package com.edgedo.timetask;


import com.edgedo.common.util.RedisUtil;
import com.edgedo.constant.WxCommonReidsKeyConstant;
import com.edgedo.sys.queryvo.SysWxConfigView;
import com.edgedo.sys.service.SysWxConfigService;
import com.edgedo.sys.service.SysWxPayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ConditionalOnProperty(
        value = {"timetask.LoadWxConfigTimeTask.enable"},
        havingValue = "true",
        matchIfMissing = false
)
@Component
public class LoadWxConfigTimeTask {

    @Autowired
    private SysWxConfigService sysWxConfigService;
    @Autowired
    private SysWxPayConfigService sysWxPayConfigService;
    @Autowired
    private RedisUtil redisUtil;

    //加载微信的配置 0/2 * * * * ?
    @Scheduled(cron = "${timetask.LoadWxConfigTimeTask.cron.loadWxConfig}")
    public void loadWxConfig(){
        //将微信的配置信息加载到redis缓存里
        List<SysWxConfigView> listWxConfig = sysWxConfigService.selectAllConfig();
        //加载微信的配置 - token
        Map<String,Object> wxConfigMap = new HashMap<>();
        for (SysWxConfigView wxConfigView :listWxConfig ){
            wxConfigMap.put(wxConfigView.getId(),wxConfigView);
        }
        //将微信配置放到redis中
        redisUtil.hmset(WxCommonReidsKeyConstant.WX_CONFIG_MAP_KEY,wxConfigMap);
        //加载微信支付配置
    }

}
