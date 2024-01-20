package com.casa.samala.mapper;

import com.casa.samala.controller.response.BillResponse;
import com.casa.samala.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * BillTypeMapper
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillTypeMapper.java, v 0.1 2024-01-20  16.18 Ahmad Isyfalana Amin Exp $
 */
@Mapper(componentModel = "spring")
public interface BillMapper {

    @Mapping(source = "person.id", target = "person.id")
    @Mapping(source = "person.fullName", target = "person.fullName")
    @Mapping(source = "billType.id", target = "billType.id")
    @Mapping(source = "billType.name", target = "billType.name")
    @Mapping(source = "paymentMethod.id", target = "paymentMethod.id")
    @Mapping(source = "paymentMethod.name", target = "paymentMethod.name")
    @Mapping(source = "createdTime", target = "createdTime")
    BillResponse toBillResponse(Bill bill);

}