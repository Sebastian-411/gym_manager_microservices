package com.icesi.edu.co.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.YearMonth;

import com.icesi.edu.co.config.YearMonthAttributeConverter;

@Data
@Entity
public class Payment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private IdMember memberId;

    private int amountPaid;

    @Convert(converter = YearMonthAttributeConverter.class)
    private YearMonth paymentMonth;
}
