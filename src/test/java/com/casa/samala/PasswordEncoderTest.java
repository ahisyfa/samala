/**
 * Dana.id
 * Copyright (c) 2017-2024 All Rights Reserved.
 */
package com.casa.samala;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: PasswordEncoderTest.java, v 0.1 2024-01-04  19.29 Ahmad Isyfalana Amin Exp $
 */
public class PasswordEncoderTest {

    @Test
    void genaratePasswordUsingBCriptPasswordEncoder_Sccess() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("12345"));
    }
}