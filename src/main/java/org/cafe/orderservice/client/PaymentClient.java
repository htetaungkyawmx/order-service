package org.cafe.orderservice.client;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.cafe.orderservice.domain.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class PaymentClient {
    @Value("${payment-service.host}")
    private String host;
    @Value("${payment-service.port}")
    private String port;
    @Value("${payment-service.basePath}")
    private String basePath;

    private String url;

    @Autowired
    @Qualifier("paymentRestTemplate")
    private RestTemplate restTemplate;

    @PostConstruct
    private void postConstruct() {
        this.url = "http://" + host + ":" + port + "/" + basePath + "/";
    }

    public String pay(PaymentRequest paymentRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(paymentRequest, headers);
            String buildURL = url + "create-payment";
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(buildURL, requestEntity, String.class);
            String result = responseEntity.getBody();
            log.info("Payment request result: {}" + result);
            return result;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
