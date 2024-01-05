package com.casa.samala.repository;

import com.casa.samala.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NotificationTypeRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: NotificationTypeRepository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {

    @Override
    List<NotificationType> findAll();

}