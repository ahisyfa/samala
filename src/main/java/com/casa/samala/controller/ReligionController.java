package com.casa.samala.controller;

import com.casa.samala.entity.Religion;
import com.casa.samala.repository.ReligionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: ReligionController.java, v 0.1 2023-12-30  15.26 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/religion")
public class ReligionController {

    @Autowired
    ReligionRepository religionRepository;

    @GetMapping("get_all")
    public List<Religion> getAllReligion() {
        return religionRepository.findAll();
    }
}