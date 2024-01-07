package com.casa.samala.controller;

import com.casa.samala.entity.PaymentMethod;
import com.casa.samala.repository.PaymentMethodRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PaymentMethodController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: PaymentMethodController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/payment_method")
@Tag(name = "Payment Method")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @GetMapping("/get_all")
    public List<PaymentMethod> getAll() {
        return paymentMethodRepository.findAll();
    }

}