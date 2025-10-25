package ru.big198801.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.big198801.model.dto.PaymentDto;
import ru.big198801.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<Void> pay(@RequestBody PaymentDto paymentDto) {
        log.info("Payment received: {}", paymentDto);
        paymentService.pay(paymentDto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/search/all-payments")
    public List<PaymentDto> searchAllPaymentsByUserId(@RequestParam(name = "userId") Long userId) {
        log.info("Search all payments by userId: {}", userId);
        return paymentService.searchAllPaymentsByUserId(userId);
    }
}
