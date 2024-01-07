package com.casa.samala.controller;

import com.casa.samala.controller.request.PersonInsertOrUpdateRequest;
import com.casa.samala.entity.Education;
import com.casa.samala.entity.FamilyRole;
import com.casa.samala.entity.Person;
import com.casa.samala.entity.Religion;
import com.casa.samala.entity.ResidenceBlock;
import com.casa.samala.repository.EducationRepository;
import com.casa.samala.repository.FamilyRoleRepository;
import com.casa.samala.repository.PersonRepository;
import com.casa.samala.repository.ReligionRepository;
import com.casa.samala.repository.ResidenceBlockRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import java.util.List;
import java.util.Optional;

/**
 * PersonController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: PersonController.java, v 0.1 2024-01-05  21.20 Ahmad Isyfalana Amin Exp $
 */
@RestController
@RequestMapping("/person")
@Tag(name = "Person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ReligionRepository religionRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private FamilyRoleRepository familyRoleRepository;

    @Autowired
    private ResidenceBlockRepository residenceBlockRepository;

    @GetMapping("/get_all")
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@Valid @RequestBody PersonInsertOrUpdateRequest request) {
        Optional<Religion> optionalReligion = religionRepository.findById(request.getReligionId());
        Optional<Education> optionalEducation = educationRepository.findById(request.getEducationId());
        Optional<FamilyRole> familyRoleOptional = familyRoleRepository.findById(request.getFamilyRoleId());
        Optional<ResidenceBlock> optionalResidenceBlock = residenceBlockRepository.findById(request.getResidenceBlockId());

        Person person = new Person();
        BeanUtils.copyProperties(request, person);
        person.setId(null);
        person.setReligion(optionalReligion.orElse(null));
        person.setEducation(optionalEducation.orElse(null));
        person.setFamilyRole(familyRoleOptional.orElse(null));
        person.setResidenceBlock(optionalResidenceBlock.orElse(null));

        Person savedPerson = personRepository.save(person);

        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Person> updatePerson(@Valid @RequestBody PersonInsertOrUpdateRequest request) {
        Optional<Religion> optionalReligion = religionRepository.findById(request.getReligionId());
        Optional<Education> optionalEducation = educationRepository.findById(request.getEducationId());
        Optional<FamilyRole> familyRoleOptional = familyRoleRepository.findById(request.getFamilyRoleId());
        Optional<ResidenceBlock> optionalResidenceBlock = residenceBlockRepository.findById(request.getResidenceBlockId());

        Person person = new Person();
        BeanUtils.copyProperties(request, person);
        person.setReligion(optionalReligion.orElse(null));
        person.setEducation(optionalEducation.orElse(null));
        person.setFamilyRole(familyRoleOptional.orElse(null));
        person.setResidenceBlock(optionalResidenceBlock.orElse(null));

        Person savedPerson = personRepository.save(person);

        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

}