package com.casa.samala.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Notification extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "notification_type_id")
    private NotificationType notificationType;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person personId;

    @Column(name = "content")
    private String content;

    @Column(name = "already_read")
    private boolean alreadyRead;

}