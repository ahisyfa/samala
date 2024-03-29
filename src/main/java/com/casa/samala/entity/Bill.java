package com.casa.samala.entity;

import com.casa.samala.enums.BillStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * NotificationType
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: FamilyRole.java, v 0.1 2024-01-05  19.11 Ahmad Isyfalana Amin Exp $
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Bill extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_type_id")
    private BillType billType;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "secretary_id")
    private Person secretary;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "residence_block_id")
    private ResidenceBlock residenceBlock;

    @Column(name = "billSnapshot")
    private String billSnapshot;

    @Column(name = "nominal")
    private Long nominal;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @Column(name = "period")
    @Temporal(TemporalType.DATE)
    private LocalDate period;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BillStatusEnum status;

}