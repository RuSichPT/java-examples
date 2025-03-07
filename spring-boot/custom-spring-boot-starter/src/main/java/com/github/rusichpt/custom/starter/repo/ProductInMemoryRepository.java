package com.github.rusichpt.custom.starter.repo;

import com.github.rusichpt.custom.starter.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Product save(Product product) {
        products.add(product);
        return product;
    }
}
