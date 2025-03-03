package com.github.rusichpt.custom.starter.service;

import com.github.rusichpt.custom.starter.entity.Product;
import com.github.rusichpt.custom.starter.repo.ProductInMemoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductInMemoryService implements ProductService {
    private final ProductInMemoryRepository repo;

    @Override
    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return repo.findAll();
    }
}
