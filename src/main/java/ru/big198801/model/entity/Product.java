package ru.big198801.model.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;

@Entity
@Table(name = "products")
@RequiredArgsConstructor
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "balance")
    private Double balance;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="payment_id")
    @ToString.Exclude
    private Payment payment;

    public enum ProductType{
        ACCOUNT, CARD
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public Product setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public Product setBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public Product setProductType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public Product setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Product setPayment(Payment payment) {
        this.payment = payment;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", productType=" + productType.toString() +
                ", createdAt=" + createdAt +
                '}';
    }
}