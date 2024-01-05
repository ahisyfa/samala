package com.casa.samala.repository;

import com.casa.samala.entity.ResidenceBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ResidenceBlockRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: ResidenceBlockRepository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface ResidenceBlockRepository extends JpaRepository<ResidenceBlock, Long> {

    @Override
    List<ResidenceBlock> findAll();

}