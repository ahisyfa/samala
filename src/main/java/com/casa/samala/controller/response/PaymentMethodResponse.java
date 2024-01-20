package com.casa.samala.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: PaymentMethodResponse.java, v 0.1 2024-01-20  16.49 Ahmad Isyfalana Amin Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodResponse {

    private Long id;

    private String name;

}