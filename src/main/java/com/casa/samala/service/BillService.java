package com.casa.samala.service;

import com.casa.samala.controller.request.BillConfirmRequest;
import com.casa.samala.controller.request.BillGetAllTransactionRequest;
import com.casa.samala.controller.request.BillGetLastTransactionRequest;
import com.casa.samala.controller.request.BillInsertOrUpdateRequest;
import com.casa.samala.controller.response.BillResponse;
import com.casa.samala.entity.Bill;
import com.casa.samala.entity.BillPeriodTypeEnum;
import com.casa.samala.entity.BillStatusEnum;
import com.casa.samala.entity.BillType;
import com.casa.samala.entity.PaymentMethod;
import com.casa.samala.entity.Person;
import com.casa.samala.entity.ResidenceBlock;
import com.casa.samala.mapper.BillMapper;
import com.casa.samala.repository.BillRepository;
import com.casa.samala.repository.BillTypeRepository;
import com.casa.samala.repository.PaymentMethodRepository;
import com.casa.samala.repository.PersonRepository;
import com.casa.samala.repository.ResidenceBlockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * BillService
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillService.java, v 0.1 2024-01-20  16.46 Ahmad Isyfalana Amin Exp $
 */
@Service
public class BillService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillTypeRepository billTypeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ResidenceBlockRepository residenceBlockRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Transactional
    public BillResponse add(BillInsertOrUpdateRequest request, BillStatusEnum statusEnum) throws JsonProcessingException {
        validationService.validate(request);

        Optional<BillType> optionalBillType = billTypeRepository.findById(request.getBillTypeId());
        if (optionalBillType.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown billTypeId " + request.getBillTypeId());
        }

        Optional<Person> optionalPerson = personRepository.findById(request.getPersonId());
        if (optionalPerson.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown personId " + request.getPersonId());
        }

        Optional<Person> optionalSecretary = personRepository.findById(request.getSecretaryId());
        if (optionalSecretary.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown personId/secretaryId " + request.getSecretaryId());
        }


        Optional<ResidenceBlock> optionalResidenceBlockId = residenceBlockRepository.findById(request.getResidenceBlockId());
        if (optionalResidenceBlockId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown residenceBlockId " + request.getResidenceBlockId());
        }

        Optional<PaymentMethod> optionalPaymentMethod = paymentMethodRepository.findById(request.getPaymentMethodId());
        if (optionalPaymentMethod.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown paymentMethod " + request.getPaymentMethodId());
        }

        // Validate Amount
        BillType billType = optionalBillType.get();
        if (billType.getNominal() > 0 && !billType.getNominal().equals(request.getNominal())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Wrong nominal: %d, the nominal of billType %s should be %d.",
                            request.getNominal(), billType.getName(), billType.getNominal()));
        }

        if (billType.getPeriodType() == BillPeriodTypeEnum.MONTHLY) {
            // Validate Period by residenceBlockId-billType-period
            Bill checkBillPeriod = billRepository.findBillByResidenceIDAndBillTypeIdAndYearAndMonth(
                    optionalResidenceBlockId.get().getId(),
                    optionalBillType.get().getId(),
                    request.getPeriod().getYear(),
                    request.getPeriod().getMonthValue()
            );

            if (checkBillPeriod != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Bill already paid, period %04d-%02d.",
                                request.getPeriod().getYear(), request.getPeriod().getMonthValue()));
            }
        }


        Bill bill = Bill.builder()
                .id(null)
                .billType(optionalBillType.get())
                .person(optionalPerson.get())
                .secretary(optionalSecretary.get())
                .residenceBlock(optionalResidenceBlockId.get())
                .nominal(request.getNominal())
                .paymentMethod(optionalPaymentMethod.get())
                .period(request.getPeriod())
                .billSnapshot(objectMapper.writeValueAsString(optionalBillType.get()))
                .build();


        bill.setStatus(statusEnum == null ? BillStatusEnum.INIT : statusEnum);

        Bill saved = billRepository.save(bill);

        return billMapper.toBillResponse(saved);
    }

    @Transactional
    public BillResponse update(BillInsertOrUpdateRequest request, BillStatusEnum statusEnum) throws JsonProcessingException {

        Bill existingBill = billRepository.getReferenceById(request.getId());

        if (existingBill == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Not found billId " + request.getId());
        }

        validationService.validate(request);


        Optional<BillType> optionalBillType = billTypeRepository.findById(request.getBillTypeId());
        if (optionalBillType.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown billTypeId " + request.getBillTypeId());
        }

        Optional<Person> optionalPerson = personRepository.findById(request.getPersonId());
        if (optionalPerson.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown personId " + request.getPersonId());
        }

        Optional<Person> optionalSecretary = personRepository.findById(request.getSecretaryId());
        if (optionalSecretary.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown personId/secretaryId " + request.getSecretaryId());
        }


        Optional<ResidenceBlock> optionalResidenceBlockId = residenceBlockRepository.findById(request.getResidenceBlockId());
        if (optionalResidenceBlockId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown residenceBlockId " + request.getResidenceBlockId());
        }

        Optional<PaymentMethod> optionalPaymentMethod = paymentMethodRepository.findById(request.getPaymentMethodId());
        if (optionalPaymentMethod.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown paymentMethod " + request.getPaymentMethodId());
        }

        Bill bill = Bill.builder()
                .id(request.getId())
                .billType(optionalBillType.get())
                .person(optionalPerson.get())
                .secretary(optionalSecretary.get())
                .residenceBlock(optionalResidenceBlockId.get())
                .nominal(request.getNominal())
                .paymentMethod(optionalPaymentMethod.get())
                .period(request.getPeriod())
                .billSnapshot(objectMapper.writeValueAsString(optionalBillType.get()))
                .build();

        if (statusEnum != null) {
            bill.setStatus(statusEnum);
        } else {
            bill.setStatus(existingBill.getStatus());
        }

        Bill saved = billRepository.save(bill);

        return billMapper.toBillResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<BillResponse> getLastTransaction(BillGetLastTransactionRequest request){
        Specification<Bill> specification = new Specification<Bill>() {
            @Override
            public Predicate toPredicate(Root<Bill> root, CriteriaQuery<?> query, CriteriaBuilder criteria) {
                List<Predicate> predicates = new ArrayList<>();

                if (Objects.nonNull(request.getPersonId())) {
                    Join<Object, Object> joinPerson = root.join("person", JoinType.INNER);
                    predicates.add(criteria.equal(joinPerson.get("id"), request.getPersonId()));
                }

                if (Objects.nonNull(request.getBillTypeId())) {
                    Join<Object, Object> joinPerson = root.join("billType", JoinType.INNER);
                    predicates.add(criteria.equal(joinPerson.get("id"), request.getBillTypeId()));
                }

                if (!StringUtils.isEmpty(request.getResidenceBlockName())) {
                    Join<Object, Object> joinPerson = root.join("residenceBlock", JoinType.INNER);
                    predicates.add(criteria.equal(joinPerson.get("name"), request.getResidenceBlockName()));
                }

                List<Order> orders = new ArrayList<>();
                orders.add(criteria.desc(root.get("createdTime")));

                return query
                        .where(predicates.toArray(new Predicate[]{}))
                        .orderBy(orders)
                        .getRestriction();
            }
        };

        Pageable pageable = PageRequest.of(0, request.getSize());
        Page<Bill> bills = billRepository.findAll(specification, pageable);

        List<BillResponse> billResponses = bills
                .getContent()
                .stream()
                .map(bill -> billMapper.toBillResponse(bill))
                .toList();

        return new PageImpl<>(billResponses, pageable, bills.getTotalElements());
    }

    @Transactional(readOnly = true)
    public List<BillResponse> getAllTransaction(BillGetAllTransactionRequest request){
        Specification<Bill> specification = new Specification<Bill>() {
            @Override
            public Predicate toPredicate(Root<Bill> root, CriteriaQuery<?> query, CriteriaBuilder criteria) {
                List<Predicate> predicates = new ArrayList<>();

                if (Objects.nonNull(request.getPersonId())) {
                    Join<Object, Object> joinPerson = root.join("person", JoinType.INNER);
                    predicates.add(criteria.equal(joinPerson.get("id"), request.getPersonId()));
                }

                if (Objects.nonNull(request.getBillTypeId())) {
                    Join<Object, Object> joinPerson = root.join("billType", JoinType.INNER);
                    predicates.add(criteria.equal(joinPerson.get("id"), request.getBillTypeId()));
                }

                if (!StringUtils.isEmpty(request.getResidenceBlockName())) {
                    Join<Object, Object> joinPerson = root.join("residenceBlock", JoinType.INNER);
                    predicates.add(criteria.equal(joinPerson.get("name"), request.getResidenceBlockName()));
                }

                if (!Objects.nonNull(request.getStartDate()) && !Objects.nonNull(request.getEndDate())) {
                    predicates.add(criteria.between(root.get("createdTime"), request.getStartDate(), request.getEndDate()));
                }

                List<Order> orders = new ArrayList<>();
                orders.add(criteria.asc(root.get("createdTime")));

                return query
                        .where(predicates.toArray(new Predicate[]{}))
                        .orderBy(orders)
                        .getRestriction();
            }
        };

        return billRepository
                .findAll(specification)
                .stream()
                .map(bill -> billMapper.toBillResponse(bill))
                .toList();
    }

    @Transactional
    public BillResponse confirm(BillConfirmRequest request, BillStatusEnum statusEnum) {
        Bill existingBill = billRepository.getReferenceById(request.getId());

        if (existingBill == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Not found billId " + request.getId());
        }

        Optional<Person> optionalSecretary = personRepository.findById(request.getSecretaryId());
        if (optionalSecretary.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown personId/secretaryId " + request.getSecretaryId());
        }

        existingBill.setStatus(statusEnum);
        existingBill.setSecretary(optionalSecretary.get());

        Bill saved = billRepository.save(existingBill);

        return billMapper.toBillResponse(saved);
    }

}