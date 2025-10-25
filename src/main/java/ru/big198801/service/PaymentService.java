package ru.big198801.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.big198801.mapper.PaymentMapper;
import ru.big198801.mapper.ProductsMapper;
import ru.big198801.model.dto.PaymentDto;
import ru.big198801.model.entity.Payment;
import ru.big198801.model.entity.Product;
import ru.big198801.model.entity.User;
import ru.big198801.repository.PaymentsRepository;
import ru.big198801.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final UsersRepository usersRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentsRepository paymentsRepository;
    private final ProductsMapper productsMapper;

    public void pay(PaymentDto paymentDto) {
        User users = usersRepository.findById(paymentDto.userId()).orElseThrow(
                () -> {
                    log.error("User not found");
                    return new EntityNotFoundException("User not found");
                }
        );

        Payment payment = paymentMapper.toEntity(paymentDto);
        Set<Product> productsList = productsMapper.toEntitySet(paymentDto.productsDtoList());
        payment.setProducts(productsList);
        for (Product products : productsList) {
            payment.addProduct(products);
        }
        users.getPayments().add(payment);
        users.addPayment(payment);
        usersRepository.save(users);
    }

    public List<PaymentDto> searchAllPaymentsByUserId(Long userId) {
        List<Payment> paymentList = paymentsRepository.findByUserId(userId).orElseThrow(
                () -> {
                    log.error("У пользователя = {} не найдены платежи", userId);
                    return new EntityNotFoundException("Платежи у пользователя = " + userId + " не найдены");
                }
        );

        return paymentMapper.toDtoList(paymentList);
    }
}