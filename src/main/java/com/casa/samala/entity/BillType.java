package com.casa.samala.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class BillType extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "period_type")
    private String periodType;

    @Column(name = "nominal")
    private Long nominal;

    @Column(name = "active")
    private boolean active;

}