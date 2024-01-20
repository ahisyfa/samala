package com.casa.samala.mapper;

import com.casa.samala.controller.request.BillTypeInsertOrUpdateRequest;
import com.casa.samala.controller.response.BillTypeResponse;
import com.casa.samala.entity.BillType;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * BillTypeMapper
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BillTypeMapper.java, v 0.1 2024-01-20  16.18 Ahmad Isyfalana Amin Exp $
 */
@Mapper(componentModel = "spring")
public interface BillTypeMapper {

    BillTypeResponse toBillTypeResponse(BillType billType);


    BillType toBillType(BillTypeInsertOrUpdateRequest request);


    List<BillTypeResponse> toBillTypeResponse(Collection<BillType> billTypes);

}