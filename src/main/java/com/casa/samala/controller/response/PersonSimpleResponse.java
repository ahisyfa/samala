package com.casa.samala.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: PersonSimpleResponse.java, v 0.1 2024-01-20  22.26 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersonSimpleResponse {

    private Long id;

    private String fullName;

}