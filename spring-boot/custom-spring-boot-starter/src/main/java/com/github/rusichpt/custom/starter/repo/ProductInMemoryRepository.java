package com.github.rusichpt.custom.starter.repo;

import com.github.rusichpt.custom.starter.model.Product;
import com.github.rusichpt.custom.starter.service.ProductInMemoryService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@ConditionalOnBean(ProductInMemoryService.class)
public class ProductInMemoryRepository {
    private final List<Product> products = new ArrayList<>();

    public ProductInMemoryRepository() {
        products.add(new Product(1L, "Xiaomi Смартфон Redmi", 4898));
        products.add(new Product(2L, "Realme Смартфон Note 50", 4898));
        products.add(new Product(3L, "Infinix Смартфон GT 10 Pro", 4898));
        products.add(new Product(4L, "Tecno Смартфон Spark 20 Pro+", 4898));
    }

    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst();
    }

    public List<Product> findAll() {
        return products;
    }
}
