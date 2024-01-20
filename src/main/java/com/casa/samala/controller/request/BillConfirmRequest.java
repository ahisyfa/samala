package com.casa.samala.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BillConfirmRequest
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillConfirmRequest.java, v 0.1 2024-01-20  23.57 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillConfirmRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long secretaryId;

}