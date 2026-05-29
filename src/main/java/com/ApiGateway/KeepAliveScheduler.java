package com.ApiGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class KeepAliveScheduler {

    private static final Logger log = LoggerFactory.getLogger(KeepAliveScheduler.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final List<String> BACKEND_HEALTH_URLS = List.of(
    	    "https://nookaccount.onrender.com/health",
    	    "https://nookpgdetails.onrender.com/health",
    	    "https://nookaddpg.onrender.com/health",
    	    "https://nooklyinquiry.onrender.com/health"
    	);

    @Scheduled(fixedDelay = 300_000)
    public void keepBackendsAlive() {
        log.info("Starting keep-alive ping for backend services");
        
        for (String url : BACKEND_HEALTH_URLS) {
            try {
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    log.info("Keep-alive OK: {}", url);
                } else {
                    log.warn("Keep-alive returned {} for {}", response.getStatusCode(), url);
                }
            } catch (Exception e) {
                log.error("Keep-alive failed for {}: {}", url, e.getMessage());
            }
        }
    }
}