package com.casa.samala.controller;

import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.entity.Religion;
import com.casa.samala.repository.ReligionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: ReligionController.java, v 0.1 2023-12-30  15.26 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/religion")
@Tag(name = "Religion")
public class ReligionController {

    @Autowired
    ReligionRepository religionRepository;

    @GetMapping(value = "get_all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Gets All Religion", description = "Gets All Religion")
    public ResponseEntity<ApiResponse<List<Religion>>> getAllReligion() {
        ApiResponse<List<Religion>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(religionRepository.findAll());

        return ResponseEntity.ok(apiResponse);
    }

}