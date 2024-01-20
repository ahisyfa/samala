package com.casa.samala.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * BillTypeResponse
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillTypeResponse.java, v 0.1 2024-01-05  19.11 Ahmad Isyfalana Amin Exp $
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BillTypeSimpleResponse {

    private Long id;

    private String name;

}