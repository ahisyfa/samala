package com.casa.samala.controller;

import com.casa.samala.controller.request.NotificationMarkAsReadRequest;
import com.casa.samala.controller.request.NotificationSearchRequest;
import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.controller.response.NotificationResponse;
import com.casa.samala.entity.Notification;
import com.casa.samala.entity.NotificationType;
import com.casa.samala.enums.NotificationTypeEnum;
import com.casa.samala.mapper.NotificationMapper;
import com.casa.samala.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * NotificationController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: NotificationController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/notification")
@Tag(name = "Notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationMapper notificationMapper;

    @GetMapping(value = "/get_last_notification",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Last Notification")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getLastNotification(
            @RequestParam(value = "personId",          required = true ) Long personId,
            @RequestParam(value = "residenceBlockName",required = false) String residenceBlockName,
            @RequestParam(value = "notificationType",  required = false) NotificationTypeEnum notificationType,
            @RequestParam(value = "unreadOnly",        required = false) boolean unreadOnly,
            @RequestParam(value = "size",              required = true ) int size
    ) {
        NotificationSearchRequest request = NotificationSearchRequest
                .builder()
                .personId(personId)
                .residenceBlockName(residenceBlockName)
                .notificationType(notificationType)
                .unreadOnly(unreadOnly)
                .size(size)
                .build();

        List<Notification> searchedNotification = notificationService.getSearchedNotification(request);
        List<NotificationResponse> responses = searchedNotification
                .stream()
                .map(notification -> notificationMapper.toNotificationResponse(notification))
                .toList();

        ApiResponse<List<NotificationResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(responses);

        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping(value = "/mark_as_read",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mark Notification Already Read")
    public ResponseEntity<ApiResponse<String>> markAsRead(@RequestBody NotificationMarkAsReadRequest request) {
        Notification notification = notificationService.updateAsAlreadyRead(request);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData("OK");

        return ResponseEntity.ok(apiResponse);
    }

}