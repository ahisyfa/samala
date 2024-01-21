package com.casa.samala.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NotificationMarkAsReadRequest
 * @author Ahmad Isyfalana Amin
 * @version $Id: NotificationMarkAsReadRequest.java, v 0.1 2024-01-21  10.38 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationMarkAsReadRequest {

    @NotNull
    Long notificationId;

}