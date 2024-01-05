package com.casa.samala.controller;

import com.casa.samala.entity.FamilyRole;
import com.casa.samala.repository.FamilyRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * FamilyRoleController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: FamilyRoleController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/family_role")
public class FamilyRoleController {

    @Autowired
    private FamilyRoleRepository familyRoleRepository;

    @GetMapping("/get_all")
    public List<FamilyRole> getAll() {
        return familyRoleRepository.findAll();
    }

}