package com.casa.samala.controller;

import com.casa.samala.controller.request.PersonInsertOrUpdateRequest;
import com.casa.samala.controller.request.SearchPersonRequest;
import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.controller.response.PagingResponse;
import com.casa.samala.controller.response.PersonResponse;
import com.casa.samala.repository.PersonRepository;
import com.casa.samala.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PersonController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: PersonController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/person")
@Tag(name = "Person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;


    @GetMapping(
            value = "/get_all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Gets Person")
    public ResponseEntity<ApiResponse<List<PersonResponse>>> getAll() {
        ApiResponse<List<PersonResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(personService.getAll());

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(
            value = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Search Person by fullName or familyCardId")
    public ResponseEntity<ApiResponse<List<PersonResponse>>> search(
            @RequestParam(value = "fullName",     required = false) String fullName,
            @RequestParam(value = "familyCardId", required = false) String familyCardId,
            @RequestParam(value = "page",         required = true, defaultValue = "0") Integer page,
            @RequestParam(value = "size",         required = true, defaultValue = "10") Integer size
    ) {
        SearchPersonRequest request = SearchPersonRequest.builder()
                .fullName(fullName)
                .familyCardId(familyCardId)
                .page(page)
                .size(size)
                .build();

        Page<PersonResponse> searchResult = personService.search(request);

        ApiResponse<List<PersonResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(searchResult.getContent());
        apiResponse.setPaging(PagingResponse.builder()
                        .currentPage(searchResult.getNumber())
                        .totalPage(searchResult.getTotalPages())
                        .size(searchResult.getSize())
                        .build());

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(
            path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add Person")
    public ResponseEntity<ApiResponse<PersonResponse>> add(@RequestBody PersonInsertOrUpdateRequest request) {
        PersonResponse savedPersonResponse = personService.add(request);
        ApiResponse<PersonResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(savedPersonResponse);

        return ResponseEntity.ok(apiResponse);
    }


    @PutMapping(
            path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update Person")
    public ResponseEntity<ApiResponse<PersonResponse>> update(@RequestBody PersonInsertOrUpdateRequest request) {
        PersonResponse savedPersonResponse = personService.update(request);
        ApiResponse<PersonResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(savedPersonResponse);

        return ResponseEntity.ok(apiResponse);
    }

}