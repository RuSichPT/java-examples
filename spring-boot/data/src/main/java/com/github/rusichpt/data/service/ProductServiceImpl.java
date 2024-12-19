package com.github.rusichpt.data.service;

import com.github.rusichpt.custom.starter.model.Product;
import com.github.rusichpt.custom.starter.service.ProductService;
import com.github.rusichpt.data.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;

    @Override
    public Optional<Product> getProduct(Long id) {
        return repo.findById(id)
                .map(entity -> new Product(entity.getId(), entity.getName(), entity.getPrice()));
    }

    @Override
    public List<Product> getProducts() {
        return repo.findAll().stream()
                .map(entity -> new Product(entity.getId(), entity.getName(), entity.getPrice()))
                .toList();
    }
}
