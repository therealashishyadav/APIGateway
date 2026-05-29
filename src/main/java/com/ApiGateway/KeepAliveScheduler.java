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

    // Use public Render URLs
    private static final List<String> BACKEND_HEALTH_URLS = List.of(
    	    "https://nookaccount.onrender.com/health",
    	    "https://nookpgdetails.onrender.com/health",
    	    "https://nookaddpg.onrender.com/health",
    	    "https://nooklyinquiry.onrender.com/health"
    	);

    // Run every 10 minutes (600,000 ms) to avoid rate limiting
    @Scheduled(fixedDelay = 600_000)
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

            // Wait 2 seconds before next request to avoid burst rate limiting
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                log.warn("Delay interrupted");
            }
        }
    }
}