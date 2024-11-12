package com.github.rusichpt.dataspringbootstarter.mapper;

import com.github.rusichpt.dataspringbootstarter.dto.ProductDTO;
import com.github.rusichpt.dataspringbootstarter.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProductMapper {
    @Mapping(target = "productName", source = "name")
    @Mapping(target = "productPrice", source = "price")
    ProductDTO toProductDTO(Product product);
    @Mapping(target = "name", source = "productName")
    @Mapping(target = "price", source = "productPrice")
    Product toProduct(ProductDTO productDTO);
}
