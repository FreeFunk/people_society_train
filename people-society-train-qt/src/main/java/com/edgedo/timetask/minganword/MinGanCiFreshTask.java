package com.edgedo.timetask.minganword;

import com.edgedo.common.util.FileUtil;
import com.edgedo.face.entity.FaceMatchInfo;
import com.edgedo.face.service.IFaceAiService;
import com.edgedo.society.service.SocietyConfigMinganWordsService;
import com.edgedo.text.Service.ITextFenxiAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author WangZhen
 * @description
 * @date 2020/5/3
 */
@ConditionalOnProperty(
        value = {"timetask.minganword.MinGanCiFreshTask.enable"},
        havingValue = "true",
        matchIfMissing = false
)
@Component
public class MinGanCiFreshTask {

    @Autowired
    SocietyConfigMinganWordsService minganWordsService;

    @Scheduled(cron = "${timetask.minganword.MinGanCiFreshTask.cron.freshMinganWords}")
    public void freshMinganWords(){
        //刷新敏感词库
        minganWordsService.freshMinganWords();
    }

}
