package com.github.rusichpt.custom.starter.service;

import com.github.rusichpt.custom.starter.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProduct(Long id);

    List<Product> getProducts();
}
