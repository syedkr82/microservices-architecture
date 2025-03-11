package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.entity.PaymentStatus;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.service.client.OrderServiceClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderServiceClient orderServiceClient;

    public PaymentService(PaymentRepository paymentRepository, OrderServiceClient orderServiceClient) {
        this.paymentRepository = paymentRepository;
        this.orderServiceClient = orderServiceClient;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    @Transactional
    public Payment processPayment(Payment payment) {
        payment.setStatus(PaymentStatus.PENDING);
        Payment savedPayment = paymentRepository.save(payment);

        // Simulate payment logic (e.g., calling a third-party payment provider)
        if (payment.getAmount() > 0) {
            savedPayment.setStatus(PaymentStatus.SUCCESS);
            orderServiceClient.updateOrderStatus(payment.getOrderId(), "COMPLETED");
        } else {
            savedPayment.setStatus(PaymentStatus.FAILED);
            orderServiceClient.updateOrderStatus(payment.getOrderId(), "CANCELLED");
        }

        return paymentRepository.save(savedPayment);
    }
}
