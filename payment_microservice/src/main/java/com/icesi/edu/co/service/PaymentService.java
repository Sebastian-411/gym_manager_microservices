package com.icesi.edu.co.service;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.icesi.edu.co.exception.PaymentAlreadyExistsException;
import com.icesi.edu.co.model.Payment;
import com.icesi.edu.co.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RestTemplate restTemplate;


    public String eventPayment(Payment payment) {
        rabbitTemplate.convertAndSend("pagos-queue", payment);
        return "Pago agregado correctamente, despues se te notificará sobre este caso";
    }

    @RabbitListener(queues = "pagos-queue")
    public Payment addPayment(Payment payment) {

        Boolean memberAvalaible = restTemplate.getForObject(
                "http://localhost:8082/api/gym/member/exist/" + payment.getMemberId().getIdMember(), Boolean.class);

        if (!memberAvalaible) {
            throw new AmqpRejectAndDontRequeueException("Error en el pago, enviando a DLQ",
                    new RuntimeException("El miembro no existe"));
        }

        List<Payment> existingPayments = paymentRepository.findByMemberIdAndPaymentMonth(payment.getMemberId(),
                payment.getPaymentMonth());

        if (!(existingPayments.isEmpty())) {

            throw new AmqpRejectAndDontRequeueException("Error en el pago, enviando a DLQ",
                    new PaymentAlreadyExistsException("El pago ya existe"));
        }

        return paymentRepository.save(payment);

    }
}
