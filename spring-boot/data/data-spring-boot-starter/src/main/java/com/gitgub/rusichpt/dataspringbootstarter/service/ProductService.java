package com.gitgub.rusichpt.dataspringbootstarter.service;

import com.gitgub.rusichpt.dataspringbootstarter.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProduct(Long id);

    List<Product> getProducts();
}
