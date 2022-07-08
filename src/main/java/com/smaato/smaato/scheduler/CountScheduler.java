package com.smaato.smaato.scheduler;

import com.smaato.smaato.service.AcceptService;
import com.smaato.smaato.service.impl.AcceptServiceImpl;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Map;

@Component
public class CountScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptServiceImpl.class);
    @Autowired
    private AcceptService acceptService;

    @Scheduled(fixedDelay = 60000)
    public void logUniqueCounts() {
        // TODO: 07.07.2022 12:05
        String dateAndTime = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Calendar.getInstance().getTime());
        Map<String, Map<Integer, Integer>> map = acceptService.getMap();
        Map<Integer, Integer> idsMap = map.get(dateAndTime);
        LOGGER.info("Number of unique requests at time: {} are :{}", dateAndTime, idsMap.size());

        // TODO: 07.07.2022-12:05
        LocalDateTime localDateTime = new LocalDateTime().minusMinutes(10);
        map.remove(localDateTime.toString());
    }
}
