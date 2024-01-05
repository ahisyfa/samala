package com.casa.samala.controller;

import com.casa.samala.entity.ResidenceBlock;
import com.casa.samala.repository.ResidenceBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ResidenceBlockController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: ResidenceBlockController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/residence_block_owner")
public class ResidenceBlockController {

    @Autowired
    private ResidenceBlockRepository residenceBlockRepository;

    @GetMapping("/get_all")
    public List<ResidenceBlock> getAll() {
        return residenceBlockRepository.findAll();
    }

}