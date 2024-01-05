package com.casa.samala.repository;

import com.casa.samala.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EducationRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: EducationRepository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    @Override
    List<Education> findAll();

}