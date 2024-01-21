package com.casa.samala.controller;

import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.entity.Education;
import com.casa.samala.repository.EducationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * EducationController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: EducationController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/education")
@Tag(name = "Education")
public class EducationController {

    @Autowired
    private EducationRepository educationRepository;

    @GetMapping("/get_all")
    @Operation(summary = "Get All Education Level")
    public ResponseEntity<ApiResponse<List<Education>>> getAll() {
        ApiResponse<List<Education>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(educationRepository.findAll());

        return ResponseEntity.ok(apiResponse);
    }

}