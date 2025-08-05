package com.github.rusichpt.grafana.loki.tempo.controller;

import com.github.rusichpt.grafana.loki.tempo.client.PriceClient;
import com.github.rusichpt.grafana.loki.tempo.dto.Product;
import com.github.rusichpt.grafana.loki.tempo.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final PriceClient priceClient;
    private final ProductRepository productRepository;

    @GetMapping(path = "/product/{id}")
    public Product getProductDetails(@PathVariable("id") long productId){
        log.info("Getting Product and Price Details with Product Id {}", productId);
        Product product = productRepository.getProduct(productId);
        product.setPrice(priceClient.getPrice(productId));
        return product;
    }
}
