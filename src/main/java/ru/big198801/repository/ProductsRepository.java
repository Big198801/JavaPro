package ru.big198801.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.big198801.model.entity.Products;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    Optional<List<Products>> findByUserId(Long userId);
}
