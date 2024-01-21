package com.casa.samala.controller.request;

import com.casa.samala.enums.NotificationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NotificationSearchRequest
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: NotificationSearchRequest.java, v 0.1 2024-01-21  09.54 Ahmad Isyfalana Amin Exp $
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSearchRequest {

    private Long personId;

    private String residenceBlockName;

    private NotificationTypeEnum notificationType;

    private boolean unreadOnly;

    private int size;

}