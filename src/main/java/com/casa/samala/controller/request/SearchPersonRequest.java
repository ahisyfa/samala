package com.casa.samala.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: SearchPersonRequest.java, v 0.1 2024-01-20  15.26 Ahmad Isyfalana Amin Exp $
 */
@Data
@Builder
public class SearchPersonRequest {

    private String familyCardId;

    private String fullName;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;

}