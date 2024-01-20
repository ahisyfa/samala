package com.casa.samala.service;

import com.casa.samala.controller.request.PersonInsertOrUpdateRequest;
import com.casa.samala.controller.request.SearchPersonRequest;
import com.casa.samala.controller.response.PersonResponse;
import com.casa.samala.entity.Education;
import com.casa.samala.entity.FamilyRole;
import com.casa.samala.entity.Person;
import com.casa.samala.entity.Religion;
import com.casa.samala.entity.ResidenceBlock;
import com.casa.samala.mapper.PersonMapper;
import com.casa.samala.repository.EducationRepository;
import com.casa.samala.repository.FamilyRoleRepository;
import com.casa.samala.repository.PersonRepository;
import com.casa.samala.repository.ReligionRepository;
import com.casa.samala.repository.ResidenceBlockRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
 * PersonService
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: PersonService.java, v 0.1 2024-01-20  11.45 Ahmad Isyfalana Amin Exp $
 */
@Service
public class PersonService {

    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    private ReligionRepository religionRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private FamilyRoleRepository familyRoleRepository;

    @Autowired
    private ResidenceBlockRepository residenceBlockRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private PersonMapper personMapper;

    public List<PersonResponse> getAll() {
        return personMapper.toPersonResponse(personRepository.findAll());
    }

    @Transactional
    public PersonResponse add(PersonInsertOrUpdateRequest request) {
        validationService.validate(request);

        if (personRepository.existsPersonByIdKtp(request.getIdKtp())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Person with ID KTP " + request.getIdKtp() + " already exists.");
        }

        Optional<Religion> optionalReligion = religionRepository.findById(request.getReligionId());
        if (optionalReligion.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown religionID " + request.getReligionId());
        }

        Optional<Education> optionalEducation = educationRepository.findById(request.getEducationId());
        if (optionalEducation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown educationId " + request.getEducationId());
        }

        Optional<FamilyRole> familyRoleOptional = familyRoleRepository.findById(request.getFamilyRoleId());
        if (familyRoleOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown familyRoleId " + request.getFamilyRoleId());
        }

        Optional<ResidenceBlock> optionalResidenceBlock = residenceBlockRepository.findById(request.getResidenceBlockId());
        if (optionalResidenceBlock.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown residenceBlockId " + request.getResidenceBlockId());
        }

        Person person = personMapper.toPerson(request);

        person.setId(null);
        person.setReligion(optionalReligion.orElse(null));
        person.setEducation(optionalEducation.orElse(null));
        person.setFamilyRole(familyRoleOptional.orElse(null));
        person.setResidenceBlock(optionalResidenceBlock.orElse(null));

        Person savedPerson = personRepository.save(person);

        return personMapper.toPersonResponse(savedPerson);
    }

    @Transactional
    public PersonResponse update(PersonInsertOrUpdateRequest request) {
        validationService.validate(request);

        Optional<Person> firstByIdKtp = personRepository.findFirstByIdKtp(request.getIdKtp());
        if (firstByIdKtp.isPresent()) {
            Person existingPerson = firstByIdKtp.get();
            if (!Objects.equals(existingPerson.getId(), request.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID KTP already owned by other person");
            }
        }

        if (personRepository.existsPersonByIdKtp(request.getIdKtp())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Person with ID KTP " + request.getIdKtp() + " already exists.");
        }

        Optional<Religion> optionalReligion = religionRepository.findById(request.getReligionId());
        if (optionalReligion.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown religionID " + request.getReligionId());
        }

        Optional<Education> optionalEducation = educationRepository.findById(request.getEducationId());
        if (optionalEducation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown educationId " + request.getEducationId());
        }

        Optional<FamilyRole> familyRoleOptional = familyRoleRepository.findById(request.getFamilyRoleId());
        if (familyRoleOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown familyRoleId " + request.getFamilyRoleId());
        }

        Optional<ResidenceBlock> optionalResidenceBlock = residenceBlockRepository.findById(request.getResidenceBlockId());
        if (optionalResidenceBlock.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown residenceBlockId " + request.getResidenceBlockId());
        }

        Person person = personMapper.toPerson(request);

        person.setReligion(optionalReligion.orElse(null));
        person.setEducation(optionalEducation.orElse(null));
        person.setFamilyRole(familyRoleOptional.orElse(null));
        person.setResidenceBlock(optionalResidenceBlock.orElse(null));

        Person savedPerson = personRepository.save(person);

        return personMapper.toPersonResponse(savedPerson);
    }

    @Transactional(readOnly = true)
    public Page<PersonResponse> search(SearchPersonRequest request) {
        Specification<Person> specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteria) {
                List<Predicate> predicates = new ArrayList<>();

                if (Objects.nonNull(request.getFullName())) {
                    predicates.add(criteria.or(
                            criteria.like(criteria.lower(root.get("fullName")), "%" + request.getFullName().toLowerCase()  + "%")
                    ));
                }

                if (Objects.nonNull(request.getFamilyCardId())) {
                    predicates.add(criteria.or(
                            criteria.like(criteria.lower(root.get("familyCardId")), "%" + request.getFamilyCardId().toLowerCase()  + "%")
                    ));
                }

                return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
            }
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Person> persons = personRepository.findAll(specification, pageable);

        List<PersonResponse> personResponses = persons
                .getContent()
                .stream()
                .map(person -> personMapper.toPersonResponse(person))
                .toList();

        return new PageImpl<>(personResponses, pageable, persons.getTotalElements());
    }
}