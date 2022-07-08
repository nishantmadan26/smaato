package com.smaato.smaato.service.impl;

import com.smaato.smaato.service.AcceptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AcceptServiceImpl implements AcceptService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptServiceImpl.class);
    Map<String, Map<Integer, Integer>> map = new HashMap<>();

    @Override
    public void accept(int id, String httpUrl) {
        LOGGER.info("Processing Request with id:{}, url:{}", id, httpUrl);
        // TODO: 07.07.2022-12:05
        String date = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Calendar.getInstance().getTime());
        if (!map.containsKey(date)) {
            HashMap<Integer, Integer> idsMap = new HashMap<>();
            idsMap.put(id, 1);
            map.put(date, idsMap);
        } else {
            Map<Integer, Integer> idsMap = map.get(date);
            idsMap.put(id, idsMap.get(id) + 1);
            map.put(date, idsMap);
        }
        if (Objects.nonNull(httpUrl)) {
            Map<Integer, Integer> idsMap = map.get(date);
            int uniqueRequests = idsMap.size();
            /*
             * call restTemplate
             * http://localhost:8080/api/smaato/count?uniqueCounts={uniqueRequests}
             * */
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<Integer> response = restTemplate
                    .exchange("http://localhost:8080/api/smaato/count/{uniqueRequests}", HttpMethod.POST, request, Integer.class, uniqueRequests);
            LOGGER.info("Unique request per minute:{}", response);
        }

    }

    public Map<String, Map<Integer, Integer>> getMap() {
        return map;
    }
}
