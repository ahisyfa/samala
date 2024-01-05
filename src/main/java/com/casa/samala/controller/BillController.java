package com.casa.samala.controller;

import com.casa.samala.entity.Bill;
import com.casa.samala.entity.BillType;
import com.casa.samala.repository.BillRepository;
import com.casa.samala.repository.BillTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * BillController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @GetMapping("/get_all")
    public List<Bill> getAll() {
        return billRepository.findAll();
    }


}