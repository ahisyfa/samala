package com.casa.samala.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: PingController.java, v 0.1 2023-12-29  19.41 Ahmad Isyfalana Amin Exp $
 */
@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "Hello World!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response){
        String json = String.format("{\"jwtToken\": \"%s\"}", response.getHeader("Authorization"));
        HttpHeaders responseHeaders = new org.springframework.http.HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
    }

}