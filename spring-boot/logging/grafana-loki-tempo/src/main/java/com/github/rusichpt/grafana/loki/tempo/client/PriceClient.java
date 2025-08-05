package com.github.rusichpt.grafana.loki.tempo.client;

import com.github.rusichpt.grafana.loki.tempo.dto.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class PriceClient {

    private final RestTemplate restTemplate;

    public Price getPrice( long productId){
        log.info("Fetching Price Details With Product Id {}", productId);
        String url = String.format("/price/%d", productId);
        ResponseEntity<Price> price = restTemplate.getForEntity(url, Price.class);
        return price.getBody();
    }
}
