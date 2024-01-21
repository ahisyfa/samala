package com.casa.samala.controller;

import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import com.casa.samala.entity.NotificationType;
import com.casa.samala.repository.NotificationTypeRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * NotificationTypeController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: NotificationTypeController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/notification_type")
@Tag(name = "Notification Type")
@Hidden
public class NotificationTypeController {

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @GetMapping("/get_all")
    @Operation(summary = "Get All Notification Type")
    public ResponseEntity<ApiResponse<List<NotificationType>>> getAll() {
        ApiResponse<List<NotificationType>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.SUCCESS);
        apiResponse.setData(notificationTypeRepository.findAll());

        return ResponseEntity.ok(apiResponse);
    }

}