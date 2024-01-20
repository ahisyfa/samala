package com.casa.samala.mapper;

import com.casa.samala.controller.response.BillResponse;
import com.casa.samala.entity.Bill;
import org.mapstruct.Mapper;

/**
 * BillTypeMapper
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillTypeMapper.java, v 0.1 2024-01-20  16.18 Ahmad Isyfalana Amin Exp $
 */
@Mapper(componentModel = "spring")
public interface BillMapper {

    BillResponse toBillResponse(Bill bill);

}