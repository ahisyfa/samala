package com.casa.samala.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillGetLastTransaction.java, v 0.1 2024-01-20  23.08 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillGetLastTransactionRequest {

    private Long personId;

    private Long billTypeId;

    private String residenceBlockName;

    private int size;

}