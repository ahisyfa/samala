package com.casa.samala.service;

import com.casa.samala.controller.request.BillTypeInsertOrUpdateRequest;
import com.casa.samala.controller.response.BillTypeResponse;
import com.casa.samala.entity.BillType;
import com.casa.samala.mapper.BillTypeMapper;
import com.casa.samala.repository.BillTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillTypeService.java, v 0.1 2024-01-20  16.22 Ahmad Isyfalana Amin Exp $
 */
@Service
public class BillTypeService {
    @Autowired
    private BillTypeRepository billTypeRepository;

    @Autowired
    private BillTypeMapper billTypeMapper;

    @Autowired
    private ValidationService validationService;

    @Transactional(readOnly = true)
    public List<BillTypeResponse> getAll() {
        List<BillType> all = billTypeRepository.findAll();

        return billTypeMapper.toBillTypeResponse(all);
    }

    @Transactional(readOnly = true)
    public BillTypeResponse add(BillTypeInsertOrUpdateRequest request) {
        validationService.validate(request);

        BillType billType = billTypeMapper.toBillType(request);
        billType.setId(null);

        BillType savedBillType = billTypeRepository.save(billType);

        return billTypeMapper.toBillTypeResponse(savedBillType);
    }

    @Transactional(readOnly = true)
    public BillTypeResponse update(BillTypeInsertOrUpdateRequest request) {
        validationService.validate(request);

        if (billTypeRepository.existsById(request.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Not found BillType with Id: " + request.getId());
        }

        BillType billType = billTypeMapper.toBillType(request);

        BillType savedBillType = billTypeRepository.save(billType);

        return billTypeMapper.toBillTypeResponse(savedBillType);
    }

}