package com.casa.samala.controller;

import com.casa.samala.entity.ResidenceBlockOwner;
import com.casa.samala.repository.ResidenceBlockOwnerRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ResidenceBlockOwnerController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: ResidenceBlockOwnerController.java, v 0.1 2024-01-05  23.01 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/residence_block_owner")
@Tag(name = "Residence Block Owner")
public class ResidenceBlockOwnerController {

    @Autowired
    private ResidenceBlockOwnerRepository residenceBlockOwnerRepository;

    @GetMapping("/get_all")
    public List<ResidenceBlockOwner> getAll() {
        return residenceBlockOwnerRepository.findAll();
    }

}