package com.casa.samala.controller;

import com.casa.samala.entity.NotificationType;
import com.casa.samala.repository.NotificationTypeRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
public class NotificationTypeController {

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @GetMapping("/get_all")
    public List<NotificationType> getAll() {
        return notificationTypeRepository.findAll();
    }

}