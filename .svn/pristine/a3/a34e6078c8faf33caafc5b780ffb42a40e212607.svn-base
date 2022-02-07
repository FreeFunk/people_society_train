package com.edgedo.timetask;

import com.edgedo.common.util.RedisUtil;
import com.edgedo.pay.wxpay.util.WxCommonUtil;
import com.edgedo.sys.entity.SysWxConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = "app.scheduling.enable=false")
@SpringBootTest
public class LoadWxConfigTimeTaskTest {
    @Autowired
    LoadWxConfigTimeTask loadWxConfigTimeTask;
    @Autowired
    RedisUtil redisUtil;
    @Test
    public void loadWxConfig() throws Exception {
        loadWxConfigTimeTask.loadWxConfig();
    }

    @Test
    public void getSysWxConfig() throws Exception {
        SysWxConfig wxConfig = WxCommonUtil.getSysWxConfig("gh_24bb542255ae");
        System.out.println(wxConfig);
    }

    /*@Test
    public void put100Million() throws Exception {
        long start =0,end=0;
        for (int i=0;i<10000000;i++){
             start = System.currentTimeMillis();
             String key = "testKey_" + i;
             redisUtil.set(key, Guid.guid());
             end = System.currentTimeMillis();
        }
        start = System.currentTimeMillis();
//        redisUtil.del("test_key");
//        redisUtil.hget("test_key","testKey_9876" );

        Object obj = redisUtil.get("testKey_9876");
        redisUtil.expire("testKey_9876",360);
        System.out.println(obj);
        end = System.currentTimeMillis();
        System.out.println("最后一次耗时:" + (end - start)+"毫秒");
    }*/



}