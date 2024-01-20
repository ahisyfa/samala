package com.casa.samala.controller;

import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.controller.response.BillResponse;
import com.casa.samala.controller.response.LoginResponse;
import com.casa.samala.entity.User;
import com.casa.samala.mapper.PersonMapper;
import com.casa.samala.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: PingController.java, v 0.1 2023-12-29  19.41 Ahmad Isyfalana Amin Exp $
 */
@RestController
@Tag(name = "Login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PersonMapper personMapper;

    @GetMapping("/ping")
    @Operation(summary = "Check Session")
    public String ping() {
        return "Hello World!";
    }

    @PostMapping("/login")
    @Operation(summary = "Login Using Basic Authentication")
    public ResponseEntity<ApiResponse<LoginResponse>>login(HttpServletRequest request, HttpServletResponse response){
        String jwtToken = response.getHeader("Authorization");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> userByUsername = userService.findUserByUsername(authentication.getName());

        LoginResponse loginResponse = LoginResponse
                .builder()
                .jwtToken(jwtToken)
                .username(authentication.getName())
                .person(personMapper.toPersonSimpleResponse(userByUsername.get().getPerson()))
                .build();

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(loginResponse);

        return ResponseEntity.ok(apiResponse);
    }

}