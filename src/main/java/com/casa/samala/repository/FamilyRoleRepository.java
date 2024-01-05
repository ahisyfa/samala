package com.casa.samala.repository;

import com.casa.samala.entity.FamilyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FamilyRoleRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: FamilyRoleRepository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface FamilyRoleRepository extends JpaRepository<FamilyRole, Long> {

    @Override
    List<FamilyRole> findAll();

}