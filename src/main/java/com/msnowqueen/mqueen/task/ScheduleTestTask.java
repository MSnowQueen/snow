package com.msnowqueen.mqueen.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zn* on 2018/9/30
 */
@Component
@Slf4j
public class ScheduleTestTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 每五秒钟打印一次
     * 每个小时的一分一秒打印
     */
    // @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "1 1 0/1 * * ? ")
    public void scheduleTest() {
        log.info("现在的时间是 : {}, 请合理利用时间!", dateFormat.format(new Date()));
    }

}
