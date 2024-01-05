package com.casa.samala.repository;

import com.casa.samala.entity.Religion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ReligionRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: ReligionRepository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface ReligionRepository extends JpaRepository<Religion, Long> {

    @Override
    List<Religion> findAll();

}