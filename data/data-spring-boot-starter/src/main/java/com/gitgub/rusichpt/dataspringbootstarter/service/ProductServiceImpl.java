package com.gitgub.rusichpt.dataspringbootstarter.service;

import com.gitgub.rusichpt.dataspringbootstarter.entity.Product;
import com.gitgub.rusichpt.dataspringbootstarter.repo.ProductRepository;
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
        return repo.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return repo.findAll();
    }
}
