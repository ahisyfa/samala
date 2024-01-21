package com.casa.samala.controller;

import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.entity.FamilyRole;
import com.casa.samala.repository.FamilyRoleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * FamilyRoleController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: FamilyRoleController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/family_role")
@Tag(name = "Family Role")
public class FamilyRoleController {

    @Autowired
    private FamilyRoleRepository familyRoleRepository;

    @GetMapping("/get_all")
    @Operation(summary = "Get All Family Role")
    public ResponseEntity<ApiResponse<List<FamilyRole>>> getAll() {
        ApiResponse<List<FamilyRole>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(familyRoleRepository.findAll());

        return ResponseEntity.ok(apiResponse);
    }

}