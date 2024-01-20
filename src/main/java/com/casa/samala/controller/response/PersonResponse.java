package com.casa.samala.controller.response;

import com.casa.samala.entity.Education;
import com.casa.samala.entity.FamilyRole;
import com.casa.samala.entity.Religion;
import com.casa.samala.entity.ResidenceBlock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * PersonView
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: Person.java, v 0.1 2024-01-05  19.32 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersonResponse {

    private Long id;

    private String idKtp;

    private String familyCardId;

    private String fullName;

    private String gender;

    private String placeOfBirth;

    private LocalDate dateOfBirth;

    private Religion religion;

    private Education education;

    private boolean marriageStatus;

    private FamilyRole familyRole;

    private String nationality;

    private String address;

    private ResidenceBlock residenceBlock;

    private String phoneNumber;

    private String profilePictureUrl;
}