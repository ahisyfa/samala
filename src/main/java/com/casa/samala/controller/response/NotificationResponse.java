package com.casa.samala.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: NotificationResponse.java, v 0.1 2024-01-21  09.28 Ahmad Isyfalana Amin Exp $
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long id;

    private String notificationType;

    private PersonSimpleResponse person;

    private String content;

    private boolean alreadyRead;

    private LocalDateTime createdTime;

}