package com.casa.samala.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * BillGetAllTransactionRequest
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillGetAllTransactionRequest.java, v 0.1 2024-01-20  23.08 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillGetAllTransactionRequest {

    private Long personId;

    private Long billTypeId;

    private String residenceBlockName;

    private LocalDate startDate;

    private LocalDate endDate;

}