package com.casa.samala.controller;

import com.casa.samala.entity.Religion;
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

    @GetMapping("get_all")
    public List<Religion> getAllReligion() {
        List<Religion> religions = new ArrayList<>();
        religions.add(new Religion(1L, "Islam"));
        religions.add(new Religion(2L, "Kristen"));

        return religions;
    }
}