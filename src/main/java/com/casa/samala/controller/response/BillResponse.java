package com.casa.samala.controller.response;

import com.casa.samala.entity.BillStatusEnum;
import com.casa.samala.entity.BillType;
import com.casa.samala.entity.PaymentMethod;
import com.casa.samala.entity.Person;
import com.casa.samala.entity.ResidenceBlock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    private BillType billType;

    private Person person;

    private Person secretary;

    private ResidenceBlock residenceBlock;

    private String billSnapshot;

    private Long nominal;

    private PaymentMethod paymentMethod;

    private LocalDate period;

    private BillStatusEnum status;
}