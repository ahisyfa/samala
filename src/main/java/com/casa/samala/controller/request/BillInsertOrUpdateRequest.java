package com.casa.samala.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * BillInsertOrUpdateRequest
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillInsertOrUpdateRequest.java, v 0.1 2024-01-20  16.54 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillInsertOrUpdateRequest {

    private Long id;

    @NotNull
    private Long billTypeId;

    @NotNull
    private Long personId;

    @NotNull
    private Long secretaryId;

    @NotNull
    private Long residenceBlockId;

    @NotNull
    private Long nominal;

    @NotNull
    private Long paymentMethodId;

    @NotNull
    private LocalDate period;
}