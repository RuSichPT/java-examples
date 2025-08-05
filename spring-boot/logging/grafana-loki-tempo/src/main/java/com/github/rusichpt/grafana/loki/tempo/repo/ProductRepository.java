package com.github.rusichpt.grafana.loki.tempo.repo;

import com.github.rusichpt.grafana.loki.tempo.dto.Product;
import com.github.rusichpt.grafana.loki.tempo.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    public ProductRepository() {
        products.put(1L, new Product(1L, "Test 1", null));
        products.put(2L, new Product(2L, "Test 2", null));
    }

    public Product getProduct(Long productId) {
        log.info("Getting Product from Product Repo With Product Id {}", productId);
        if (!products.containsKey(productId)) {
            log.error("Product Not Found for Product Id {}", productId);
            throw new ProductNotFoundException("Product Not Found");
        }
        return products.get(productId);
    }
}
