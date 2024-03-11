package com.gitgub.rusichpt.dataspringbootstarter.controller;


import com.gitgub.rusichpt.dataspringbootstarter.entity.Product;
import com.gitgub.rusichpt.dataspringbootstarter.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/products")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id).orElse(null);
    }
}
