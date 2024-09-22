package com.icesi.edu.co.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.icesi.edu.co.model.Payment;
import com.icesi.edu.co.service.PaymentService;


@RestController
@RequestMapping("/api/gym/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_TRAINER', 'ROLE_ADMIN')")
    @Operation(summary = "Add a payment", description = "This endpoint allows adding a payment.")
    public String addPayment(
            @Parameter(description = "Payment required", required = true) @RequestBody Payment payment) {
        return paymentService.eventPayment(payment);
    }

}
