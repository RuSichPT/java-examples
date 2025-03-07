package com.github.rusichpt.custom.starter.mapper;

import com.github.rusichpt.custom.starter.dto.ProductDTO;
import com.github.rusichpt.custom.starter.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {
    @Mapping(target = "productName", source = "name")
    @Mapping(target = "productPrice", source = "price")
    ProductDTO toProductDTO(Product product);

    @Mapping(target = "name", source = "productName")
    @Mapping(target = "price", source = "productPrice")
    Product toProduct(ProductDTO productDTO);
}
