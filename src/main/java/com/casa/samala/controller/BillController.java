package com.casa.samala.controller;

import com.casa.samala.controller.request.BillConfirmRequest;
import com.casa.samala.controller.request.BillGetAllTransactionRequest;
import com.casa.samala.controller.request.BillGetLastTransactionRequest;
import com.casa.samala.controller.request.BillInsertOrUpdateRequest;
import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.controller.response.BillResponse;
import com.casa.samala.controller.response.PagingResponse;
import com.casa.samala.enums.BillStatusEnum;
import com.casa.samala.repository.BillRepository;
import com.casa.samala.service.BillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * BillController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/bill")
@Tag(name = "Bill")
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillService billService;

    @GetMapping("/get_all")
    @Operation(summary = "Get All Bill Transaction")
    public ResponseEntity<ApiResponse<List<BillResponse>>>  getBillPagination(
            @RequestParam(value = "personId",           required = false) Long personId,
            @RequestParam(value = "billTypeId",         required = false) Long billTypeId,
            @RequestParam(value = "residenceBlockName", required = false) String residenceBlockName,
            @RequestParam(value = "startDate",          required = true ) LocalDate startDate,
            @RequestParam(value = "endDate",            required = true ) LocalDate endDate
    ) {
        BillGetAllTransactionRequest request = BillGetAllTransactionRequest
                .builder()
                .personId(personId)
                .billTypeId(billTypeId)
                .residenceBlockName(residenceBlockName)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        List<BillResponse> allTransaction = billService.getAllTransaction(request);

        ApiResponse<List<BillResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(allTransaction);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_last_transaction")
    @Operation(summary = "Get Last Transaction by personId and size response")
    public ResponseEntity<ApiResponse<List<BillResponse>>> getLastTransaction(
            @RequestParam(value = "personId", required = false) Long personId,
            @RequestParam(value = "billTypeId", required = false) Long billTypeId,
            @RequestParam(value = "residenceBlockName", required = false) String residenceBlockName,
            @RequestParam(value = "size",     required = true, defaultValue = "10") int size
    ) {

        BillGetLastTransactionRequest request = BillGetLastTransactionRequest
                    .builder()
                    .personId(personId)
                    .billTypeId(billTypeId)
                    .residenceBlockName(residenceBlockName)
                    .size(size)
                    .build();

        Page<BillResponse> lastTransaction = billService.getLastTransaction(request);

        ApiResponse<List<BillResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(lastTransaction.getContent());
        apiResponse.setPaging(PagingResponse.builder()
                .currentPage(0)
                .totalPage(lastTransaction.getTotalPages())
                .size(lastTransaction.getSize())
                .build());

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(
            path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add Bill")
    public ResponseEntity<ApiResponse<BillResponse>> add(@RequestBody BillInsertOrUpdateRequest request) throws JsonProcessingException {
        BillResponse savedBill = billService.add(request, BillStatusEnum.INIT);

        ApiResponse<BillResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(savedBill);

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(
            path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update Bill")
    public ResponseEntity<ApiResponse<BillResponse>> update(@RequestBody BillInsertOrUpdateRequest request) throws JsonProcessingException {
        BillResponse savedBill = billService.update(request, null);

        ApiResponse<BillResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(savedBill);

        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping(
            path = "/confirm",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Confirm Bill Payment Already Received By Chamberlain")
    public ResponseEntity<ApiResponse<BillResponse>> confirm(@RequestBody BillConfirmRequest request) throws JsonProcessingException {
        BillResponse savedBill = billService.confirm(request, BillStatusEnum.CONFIRM);

        ApiResponse<BillResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(savedBill);

        return ResponseEntity.ok(apiResponse);
    }


}