package com.casa.samala.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.casa.samala.controller.request.BillTypeInsertOrUpdateRequest;
import com.casa.samala.entity.BillType;
import com.casa.samala.repository.BillTypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * BillTypeController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillTypeController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/bill_type")
public class BillTypeController {

    @Autowired
    private BillTypeRepository billTypeRepository;

    @GetMapping("/get_all")
    public List<BillType> getAll() {
        return billTypeRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<BillType> addBillType(@Valid @RequestBody BillTypeInsertOrUpdateRequest request) {
        BillType billType = new BillType();

        BeanUtils.copyProperties(request, billType);

        BillType savedBillType = billTypeRepository.save(billType);

        return new ResponseEntity<>(savedBillType, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<BillType> editBillType(@Valid @RequestBody BillTypeInsertOrUpdateRequest request) {
        Optional<BillType> byId = billTypeRepository.findById(request.getId());

        if (!byId.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        BillType oldBillType = byId.get();

        oldBillType.setName(request.getName());
        oldBillType.setNominal(request.getNominal());
        oldBillType.setActive(request.isActive());

        BillType savedBillType = billTypeRepository.save(oldBillType);

        return new ResponseEntity<>(savedBillType, HttpStatus.OK);
    }

}