package com.casa.samala.controller;

import com.casa.samala.controller.request.BillTypeInsertOrUpdateRequest;
import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.controller.response.BillTypeResponse;
import com.casa.samala.repository.BillTypeRepository;
import com.casa.samala.service.BillTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * BillTypeController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillTypeController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/bill_type")
@Tag(name = "Bill Type")
public class BillTypeController {

    @Autowired
    private BillTypeRepository billTypeRepository;

    @Autowired
    private BillTypeService billTypeService;

    @GetMapping(value = "/get_all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Gets All Bill Type")
    public ResponseEntity<ApiResponse<List<BillTypeResponse>>>getAll() {
        List<BillTypeResponse> billTypeResponses = billTypeService.getAll();

        ApiResponse<List<BillTypeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(billTypeResponses);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add Bill Type")
    public ResponseEntity<ApiResponse<BillTypeResponse>> add(@RequestBody BillTypeInsertOrUpdateRequest request) {
        BillTypeResponse savedBillTypeResponse = billTypeService.add(request);

        ApiResponse<BillTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(savedBillTypeResponse);

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update Bill Type")
    public ResponseEntity<ApiResponse<BillTypeResponse>> update(@RequestBody BillTypeInsertOrUpdateRequest request) {
        BillTypeResponse savedBillTypeResponse = billTypeService.update(request);

        ApiResponse<BillTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(savedBillTypeResponse);

        return ResponseEntity.ok(apiResponse);
    }

}