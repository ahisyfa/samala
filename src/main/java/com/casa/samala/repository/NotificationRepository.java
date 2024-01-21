package com.casa.samala.repository;

import com.casa.samala.entity.Notification;
import com.casa.samala.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NotificationRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: NotificationRepository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>, JpaSpecificationExecutor<Notification> {

    @Override
    List<Notification> findAll();

    List<Notification> findByPersonId(Person person);

    Notification findFirtsById(Long id);


}