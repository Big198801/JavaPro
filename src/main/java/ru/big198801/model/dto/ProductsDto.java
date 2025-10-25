package ru.big198801.model.dto;

public record ProductsDto(
        Long id,
        Long accountNumber,
        Double balance,
        String productType
) {
}
