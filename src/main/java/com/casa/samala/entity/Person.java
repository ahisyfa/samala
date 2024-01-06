package com.casa.samala.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Person
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: Person.java, v 0.1 2024-01-05  19.32 Ahmad Isyfalana Amin Exp $
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Person extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_ktp")
    private String idKtp;

    @Column(name = "family_card_id")
    private String familyCardId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    @Size(max = 1)
    private String gender;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "religion_id")
    private Religion religion;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "last_education_id")
    private Education education;

    @Column(name = "marriage_status")
    private boolean marriageStatus;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "family_role_id")
    private FamilyRole familyRole;

    @Column(name = "nationality")
    @Size(max = 3)
    private String nationality;

    @Column(name = "address")
    private String address;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "residence_block_id")
    private ResidenceBlock residenceBlock;

    @Column(name = "phone_number")
    private String phoneNumber;
}