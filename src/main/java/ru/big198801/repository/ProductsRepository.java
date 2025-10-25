package ru.big198801.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.big198801.model.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByPaymentIdIn(List<Long> paymentIds);
}