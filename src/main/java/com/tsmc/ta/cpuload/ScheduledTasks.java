package com.tsmc.ta.cpuload;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static long seq = 0;

    private static Runtime rt = Runtime.getRuntime();

    @Scheduled(fixedRate = 500)
    public void reportCurrentTime() {
        if(seq == Long.MAX_VALUE) {
            seq = 0;
        }
        log.info("The time is now {} in run {} total memory {}MB free memory {}MB available processor {}", 
        dateFormat.format(new Date()), seq++, rt.totalMemory()/(1024*1024), rt.freeMemory() / (1024*1024), rt.availableProcessors());
    }
}