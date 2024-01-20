package com.casa.samala.controller;

import com.casa.samala.controller.request.BillInsertOrUpdateRequest;
import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.controller.response.BillResponse;
import com.casa.samala.controller.response.PagingResponse;
import com.casa.samala.entity.Bill;
import com.casa.samala.entity.BillStatusEnum;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    @GetMapping("/get_last_transaction")
    public ResponseEntity<ApiResponse<List<BillResponse>>> getLastTransaction(
            @RequestParam(value = "personId", required = true) Long personId,
            @RequestParam(value = "size",     required = true) int size
    ) {

        Page<BillResponse> lastTransaction = billService.getLastTransaction(personId, size);

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

    @PostMapping(
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


}