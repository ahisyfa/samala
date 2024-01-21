package com.casa.samala.service;

import com.casa.samala.controller.request.NotificationMarkAsReadRequest;
import com.casa.samala.controller.request.NotificationSearchRequest;
import com.casa.samala.entity.Bill;
import com.casa.samala.entity.Notification;
import com.casa.samala.entity.Person;
import com.casa.samala.enums.BillStatusEnum;
import com.casa.samala.enums.NotificationTypeEnum;
import com.casa.samala.repository.NotificationRepository;
import com.casa.samala.repository.PersonRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * NotificationService
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: NotificationService.java, v 0.1 2024-01-21  09.42 Ahmad Isyfalana Amin Exp $
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional(readOnly = true)
    public List<Notification> getSearchedNotification(NotificationSearchRequest request) {
        Optional<Person> presentedPerson = personRepository.findById(request.getPersonId());

        if (presentedPerson.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown personId " + request.getPersonId());
        }
        Specification<Notification> specification = new Specification<Notification>() {
            @Override
            public Predicate toPredicate(Root<Notification> root, CriteriaQuery<?> query, CriteriaBuilder criteria) {
                List<Predicate> predicates = new ArrayList<>();

                if (Objects.nonNull(request.getPersonId())) {
                    Join<Object, Object> joinPerson = root.join("person", JoinType.INNER);
                    predicates.add(criteria.equal(joinPerson.get("id"), request.getPersonId()));
                }

                if (!StringUtils.isEmpty(request.getResidenceBlockName())) {
                    Join<Object, Object> joinPerson = root.join("residenceBlock", JoinType.INNER);
                    predicates.add(criteria.equal(joinPerson.get("name"), request.getResidenceBlockName()));
                }

                if (Objects.nonNull(request.getNotificationType())) {
                    predicates.add(criteria.equal(root.get("notificationType"), request.getNotificationType()));
                }

                if (request.isUnreadOnly()) {
                    predicates.add(criteria.equal(root.get("alreadyRead"), false));
                }

                List<Order> orders = new ArrayList<>();
                orders.add(criteria.desc(root.get("createdTime")));

                return query
                        .where(predicates.toArray(new Predicate[]{}))
                        .getRestriction();
            }
        };

        Pageable pageable = PageRequest.of(0, request.getSize());
        Page<Notification> allNotification = notificationRepository.findAll(specification, pageable);

        return allNotification.getContent().stream().toList();
    }

    @Transactional
    public Notification addNotification(Person person, NotificationTypeEnum typeEnum, Bill bill) {
        String content = "";
        if (typeEnum == NotificationTypeEnum.BILL) {

            if (bill.getStatus() == BillStatusEnum.INIT) {
                content = "Pembayaran "
                        + bill.getBillType().getName()
                        + " untuk periode " + bill.getPeriod()
                        + " berhasil tersimpan dengan status "
                        + bill.getStatus() + ".";
            } else {
                content = "Pembayaran "
                        + bill.getBillType().getName()
                        + " untuk periode " + bill.getPeriod()
                        + " sudah dikonfirmasi oleh bendahara.";
            }
        } else {
            content = "Aktivitas login terakhir tercatat pada " + LocalDateTime.now() + ".";
        }

        Notification notification = Notification
                .builder()
                .person(person)
                .notificationType(typeEnum)
                .content(content)
                .alreadyRead(false)
                .build();

        return notificationRepository.save(notification);
    }


    @Transactional
    public Notification updateAsAlreadyRead(NotificationMarkAsReadRequest request) {
        validationService.validate(request);

        Notification notification = notificationRepository.findFirtsById(request.getNotificationId());
        if (notification == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Notification not found with ID: " + request.getNotificationId());
        }

        notification.setAlreadyRead(true);

        return notificationRepository.save(notification);
    }

}