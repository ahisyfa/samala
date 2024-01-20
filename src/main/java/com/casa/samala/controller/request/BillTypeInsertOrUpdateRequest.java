package com.casa.samala.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillTypeInsertOrUpdateRequest.java, v 0.1 2024-01-05  23.27 Ahmad Isyfalana Amin Exp $
 */
@Data
public class BillTypeInsertOrUpdateRequest {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String periodType;

    @NotNull
    private Long nominal;

    @NotNull
    private boolean active;
}