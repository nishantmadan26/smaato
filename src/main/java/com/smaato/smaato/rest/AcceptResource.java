package com.smaato.smaato.rest;

import com.smaato.smaato.service.AcceptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/smaato")
public class AcceptResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptResource.class);

    @Autowired
    private AcceptService acceptService;

    @GetMapping("/accept")
    public ResponseEntity<?> accept(@RequestParam int id,
                                    @RequestParam(value = "httpUrl", required = false) String httpUrl) {
        try {
            acceptService.accept(id, httpUrl);
        } catch (Exception handleException) {
            LOGGER.error("error occurs while handling the request, exception:", handleException);
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
