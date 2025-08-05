package com.github.rusichpt.grafana.loki.tempo.controller;

import com.github.rusichpt.grafana.loki.tempo.dto.Price;
import com.github.rusichpt.grafana.loki.tempo.repo.PriceRepository;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PriceController {

    private final PriceRepository priceRepository;

    @GetMapping(path = "/price/{id}")
    @WithSpan("Получение Price")
    public Price getPrice(@PathVariable("id") long productId) {
        log.info("Getting Price details for Product Id {}", productId);
        return priceRepository.getPrice(productId);
    }
}
