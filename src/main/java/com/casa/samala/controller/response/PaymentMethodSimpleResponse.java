/**
 * Dana.id
 * Copyright (c) 2017-2024 All Rights Reserved.
 */
package com.casa.samala.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: PaymentSimpleMethod.java, v 0.1 2024-01-20  22.50 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentMethodSimpleResponse {

    private Long id;

    private String name;

}