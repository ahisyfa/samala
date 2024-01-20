package com.casa.samala.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: LoginResponse.java, v 0.1 2024-01-10  06.58 Ahmad Isyfalana Amin Exp $
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String jwtToken;

    private String username;

    private PersonSimpleResponse person;

}