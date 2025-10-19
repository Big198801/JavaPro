package ru.big198801.mapper;

import org.springframework.stereotype.Component;
import ru.big198801.model.dto.ProductsDto;
import ru.big198801.model.entity.Products;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsMapper {

    public ProductsDto toProductsDto(Products products) {
        return new ProductsDto(products.getId(), products.getAccountNumber(), products.getBalance(), String.valueOf(products.getProductType()));
    }

    public List<ProductsDto> toProductsDtoList(List<Products> productsList) {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        for (Products products : productsList) {
            productsDtoList.add(toProductsDto(products));
        }
        return productsDtoList;
    }
}
