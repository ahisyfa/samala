package com.casa.samala.repository;

import com.casa.samala.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PersonRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: PersonRepository.java,v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Override
    List<Person> findAll();

}