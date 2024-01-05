package com.casa.samala.repository;

import com.casa.samala.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PaymentMethodRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: Repository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Override
    List<PaymentMethod> findAll();

}