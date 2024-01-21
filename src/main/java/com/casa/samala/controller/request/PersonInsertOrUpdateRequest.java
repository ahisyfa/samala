package com.casa.samala.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * PersonInsertOrUpdateRequest
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: PersonInsertOrUpdateRequest.java, v 0.1 2024-01-06  06.32 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonInsertOrUpdateRequest {

    private Long id;

    @NotEmpty
    private String idKtp;

    @NotEmpty
    private String familyCardId;

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String gender;

    @NotEmpty
    private String placeOfBirth;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull
    private Long religionId;

    @NotNull
    private Long educationId;

    @NotNull
    private boolean marriageStatus;

    @NotNull
    private Long familyRoleId;

    @NotEmpty
    @Size(max = 3)
    private String nationality;

    @NotEmpty
    private String address;

    @NotNull
    private Long residenceBlockId;

    @NotEmpty
    private String phoneNumber;

    private String profilePictureUrl;

}