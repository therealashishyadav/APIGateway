//package com.ApiGateway;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//@Component
//public class KeepAliveScheduler {
//
//	private static final Logger log = LoggerFactory.getLogger(KeepAliveScheduler.class);
//
//	@Autowired
//	private RestTemplate restTemplate;
//
//	// Service URLs (public Render endpoints)
//	private static final String ACCOUNT_URL = "https://nookaccount.onrender.com/health";
//	private static final String PG_DETAILS_URL = "https://nookpgdetails.onrender.com/health";
//	private static final String ADD_PG_URL = "https://nookaddpg.onrender.com/health";
//	private static final String INQUIRY_URL = "https://nooklyinquiry.onrender.com/health";
//
//	// Every 15 minutes (900,000 ms). Stagger start times by 0s, 225s, 450s, 675s
//	// 225 seconds = 225,000 ms, 450 = 450,000, 675 = 675,000
//
//	@Scheduled(fixedDelay = 900_000, initialDelay = 0)
//	public void pingAccount() {
//		pingService(ACCOUNT_URL, "Account");
//	}
//
//	@Scheduled(fixedDelay = 900_000, initialDelay = 225_000)
//	public void pingPgDetails() {
//		pingService(PG_DETAILS_URL, "PgDetails");
//	}
//
//	@Scheduled(fixedDelay = 900_000, initialDelay = 450_000)
//	public void pingAddPg() {
//		pingService(ADD_PG_URL, "AddPg");
//	}
//
//	@Scheduled(fixedDelay = 900_000, initialDelay = 675_000)
//	public void pingInquiry() {
//		pingService(INQUIRY_URL, "Inquiry");
//	}
//
//	private void pingService(String url, String serviceName) {
//		try {
//			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//			if (response.getStatusCode().is2xxSuccessful()) {
//				log.info("Keep-alive OK for {}: {}", serviceName, url);
//			} else {
//				log.warn("Keep-alive for {} returned {}: {}", serviceName, response.getStatusCode(), url);
//			}
//		} catch (Exception e) {
//			log.error("Keep-alive failed for {} ({}): {}", serviceName, url, e.getMessage());
//		}
//	}
//}