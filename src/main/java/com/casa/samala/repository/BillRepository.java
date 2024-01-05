package com.casa.samala.repository;

import com.casa.samala.entity.Bill;
import com.casa.samala.entity.BillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BillRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillRepository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Override
    List<Bill> findAll();

}