package ru.big198801.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.big198801.mapper.ProductsMapper;
import ru.big198801.model.dto.ProductsDto;
import ru.big198801.model.entity.Products;
import ru.big198801.repository.ProductsRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductsMapper productsMapper;

    public ProductsDto getProductById(Long id) {
        log.info("Запрос продукта с id: {}", id);
        Products products = productsRepository.findById(id).orElseThrow(() -> {
            log.error("Продукт с id не найден: {}", id);
            return new EntityNotFoundException("Продукт не найден, id: " + id);
        });
        return productsMapper.toProductsDto(products);
    }

    public List<ProductsDto> getAllProducts(Long userId) {
        log.info("Запрос продукта с userId: {}", userId);
        List<Products> productsList = productsRepository.findByUserId(userId).orElseThrow(() -> {
            log.error("Продукт с userId не найден: {}", userId);
            return new EntityNotFoundException("Продукт не найден, userId: " + userId);
        });
        return productsMapper.toProductsDtoList(productsList);
    }
}