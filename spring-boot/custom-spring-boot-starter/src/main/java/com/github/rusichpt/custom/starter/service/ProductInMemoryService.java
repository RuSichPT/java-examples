package com.github.rusichpt.custom.starter.service;

import com.github.rusichpt.custom.starter.model.Product;
import com.github.rusichpt.custom.starter.repo.ProductInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@ConditionalOnMissingBean(ProductService.class)
public class ProductInMemoryService implements ProductService {
    private final ProductInMemoryRepository repo;

    @Override
    public Optional<Product> getProduct(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return repo.findAll();
    }
}
