package com.gitgub.rusichpt.dataspringbootstarter.controller;


import com.gitgub.rusichpt.dataspringbootstarter.dto.ProductDTO;
import com.gitgub.rusichpt.dataspringbootstarter.mapper.ProductMapper;
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
    private final ProductMapper mapper;

    @GetMapping(path = "/products")
    public List<ProductDTO> getAllProducts() {
        return productService.getProducts().stream()
                .map(mapper::toProductDTO)
                .toList();
    }

    @GetMapping(path = "/products/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return mapper.toProductDTO(productService.getProduct(id).orElse(null));
    }
}
