package com.icesi.edu.co.repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.edu.co.model.IdMember;
import com.icesi.edu.co.model.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByMemberIdAndPaymentMonth(IdMember memberId, YearMonth paymentMonth);
}
