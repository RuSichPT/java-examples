package com.github.rusichpt.dataspringbootstarter.service;

import com.github.rusichpt.dataspringbootstarter.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProduct(Long id);

    List<Product> getProducts();
}
