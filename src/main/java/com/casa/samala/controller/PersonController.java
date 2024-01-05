package com.casa.samala.controller;

import com.casa.samala.entity.PaymentMethod;
import com.casa.samala.entity.Person;
import com.casa.samala.repository.PaymentMethodRepository;
import com.casa.samala.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PersonController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: PersonController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/get_all")
    public List<Person> getAll() {
        return personRepository.findAll();
    }

}