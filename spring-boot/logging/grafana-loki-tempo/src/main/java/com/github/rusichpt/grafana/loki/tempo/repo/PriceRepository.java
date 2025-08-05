package com.github.rusichpt.grafana.loki.tempo.repo;

import com.github.rusichpt.grafana.loki.tempo.dto.Price;
import com.github.rusichpt.grafana.loki.tempo.exception.PriceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class PriceRepository {

    private final Map<Long, Price> prices = new ConcurrentHashMap<>();

    public PriceRepository() {
        prices.put(1L, new Price(1L, 100, 5));
        prices.put(2L, new Price(2L, 200, 3));
    }

    public Price getPrice(Long productId) {
        log.info("Getting Price from Price Repo With Product Id {}", productId);
        if (!prices.containsKey(productId)) {
            log.error("Price Not Found for Product Id {}", productId);
            throw new PriceNotFoundException("Price Not Found");
        }
        return prices.get(productId);
    }
}
