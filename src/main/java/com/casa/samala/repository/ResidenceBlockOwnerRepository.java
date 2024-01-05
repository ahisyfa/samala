package com.casa.samala.repository;

import com.casa.samala.entity.ResidenceBlockOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ResidenceBlockOwnerRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: ResidenceBlockOwnerRepository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface ResidenceBlockOwnerRepository extends JpaRepository<ResidenceBlockOwner, Long> {

    @Override
    List<ResidenceBlockOwner> findAll();

}