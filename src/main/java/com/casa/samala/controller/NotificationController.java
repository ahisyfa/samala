package com.casa.samala.controller;

import com.casa.samala.entity.Notification;
import com.casa.samala.entity.Person;
import com.casa.samala.repository.NotificationRepository;
import com.casa.samala.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * NotificationController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: NotificationController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/get_all")
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @GetMapping("/get_by_person_id/{personId}")
    public ResponseEntity<List<Notification>> getByPersonId(@PathVariable("personId") Long personId) {

        Optional<Person> presentedPerson = personRepository.findById(personId);

        if (!presentedPerson.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(notificationRepository.findAll(), HttpStatus.OK);
    }

}