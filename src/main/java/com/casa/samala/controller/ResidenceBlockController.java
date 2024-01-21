package com.casa.samala.controller;

import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.entity.ResidenceBlock;
import com.casa.samala.repository.ResidenceBlockRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ResidenceBlockController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: ResidenceBlockController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/residence_block")
@Tag(name = "Residence Block")
public class ResidenceBlockController {

    @Autowired
    private ResidenceBlockRepository residenceBlockRepository;

    @GetMapping("/get_all")
    @Operation(summary = "Get All Residence Block")
    public ResponseEntity<ApiResponse<List<ResidenceBlock>>> getAll() {
        ApiResponse<List<ResidenceBlock>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(residenceBlockRepository.findAll());

        return ResponseEntity.ok(apiResponse);
    }

}