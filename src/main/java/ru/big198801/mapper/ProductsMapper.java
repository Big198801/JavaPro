package ru.big198801.mapper;

import org.springframework.stereotype.Component;
import ru.big198801.model.dto.ProductsDto;
import ru.big198801.model.entity.Product;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ProductsMapper {

    public ProductsDto toProductsDto(Product products) {
        return new ProductsDto(products.getId(), products.getAccountNumber(), products.getBalance(),String.valueOf(products.getProductType()));
    }

    public List<ProductsDto> toProductsDtoList(List<Product> productsList) {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        if (!productsList.isEmpty()) {
            for (Product products : productsList) {
                productsDtoList.add(toProductsDto(products));
            }
        }
        return productsDtoList;
    }

    public Product toEntity(ProductsDto productsDto) {
        return new Product()
                .setAccountNumber(productsDto.accountNumber())
                .setBalance(productsDto.balance())
                .setCreatedAt(OffsetDateTime.now())
                .setProductType(Product.ProductType.valueOf(productsDto.productType()));
    }

    public Set<Product> toEntitySet(List<ProductsDto> productsDtoList) {
        Set<Product> productsList = new HashSet<>();
        if (!productsDtoList.isEmpty()) {
            for (ProductsDto productsDto : productsDtoList) {
                productsList.add(toEntity(productsDto));
            }
        }
        return productsList;
    }
}
