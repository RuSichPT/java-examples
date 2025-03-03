package com.github.rusichpt.custom.starter.mapper;

import com.github.rusichpt.custom.starter.dto.ProductDTO;
import com.github.rusichpt.custom.starter.entity.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void toProductDTO() {
        Product product = Product.builder()
                .name("Xiaomi Смартфон Redmi")
                .price(15256)
                .build();
        ProductDTO productDTO = mapper.toProductDTO(product);

        assertEquals(product.getName(), productDTO.getProductName());
        assertEquals(product.getPrice(), productDTO.getProductPrice());
    }

    @Test
    void toProduct() {
        ProductDTO productDTO = ProductDTO.builder()
                .productName("Xiaomi Смартфон Redmi")
                .productPrice(15256)
                .build();
        Product product = mapper.toProduct(productDTO);

        assertEquals(productDTO.getProductName(), product.getName());
        assertEquals(productDTO.getProductPrice(), product.getPrice());
    }
}