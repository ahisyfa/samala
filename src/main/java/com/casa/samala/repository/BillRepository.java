package com.casa.samala.repository;

import com.casa.samala.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BillRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillRepository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long>, JpaSpecificationExecutor<Bill> {

    @Override
    List<Bill> findAll();


    @Query("select b from Bill b where b.residenceBlock.id = :residenceBlockId and b.billType.id = :billTypeId and YEAR(period) = :year AND MONTH(period) = :month")
    Bill findBillByResidenceIDAndBillTypeIdAndYearAndMonth(
            @Param("residenceBlockId") Long residenceBlockId,
            @Param("billTypeId") Long billTypeId,
            @Param("year") int year,
            @Param("month") int month
    );

}