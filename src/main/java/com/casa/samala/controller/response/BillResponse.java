package com.casa.samala.controller.response;

import com.casa.samala.entity.BillStatusEnum;
import com.casa.samala.entity.ResidenceBlock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * BillResponse
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillResponse.java, v 0.1 2024-01-05  19.11 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BillResponse {

    private Long id;

    private BillTypeSimpleResponse billType;

    private PersonSimpleResponse person;

    private PersonSimpleResponse secretary;

    private ResidenceBlock residenceBlock;

    private String billSnapshot;

    private Long nominal;

    private PaymentMethodSimpleResponse paymentMethod;

    private LocalDate period;

    private BillStatusEnum status;

    private LocalDateTime createdTime;
}