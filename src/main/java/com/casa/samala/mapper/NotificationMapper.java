package com.casa.samala.mapper;

import com.casa.samala.controller.response.NotificationResponse;
import com.casa.samala.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * NotificationMapper
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: NotificationMapper.java, v 0.1 2024-01-20  16.18 Ahmad Isyfalana Amin Exp $
 */
@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(source = "person.id", target = "person.id")
    @Mapping(source = "person.fullName", target = "person.fullName")
    @Mapping(source = "notificationType", target = "notificationType")
    @Mapping(source = "createdTime", target = "createdTime")
    NotificationResponse toNotificationResponse(Notification notification);

}