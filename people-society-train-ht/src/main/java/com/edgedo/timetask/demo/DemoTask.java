package com.edgedo.timetask.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author WangZhen
 * @description
 * @date 2020/5/3
 */
@ConditionalOnProperty(
        value = {"timetask.demo.DemoTask.enable"},
        havingValue = "true",
        matchIfMissing = false
)
@Component
public class DemoTask {

    @Scheduled(cron = "${timetask.demo.DemoTask.cron.test}")
    public void test(){

    }
}
