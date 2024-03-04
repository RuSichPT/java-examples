package com.github.rusichpt.data.service;

import com.github.rusichpt.data.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProduct(Long id);

    List<Product> getProducts();
}
