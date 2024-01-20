package com.casa.samala.mapper;

import com.casa.samala.controller.request.PersonInsertOrUpdateRequest;
import com.casa.samala.controller.response.PersonResponse;
import com.casa.samala.entity.Person;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: PersonMapper.java, v 0.1 2024-01-20  12.26 Ahmad Isyfalana Amin Exp $
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toPerson(PersonInsertOrUpdateRequest personInsertOrUpdateRequest);

    PersonResponse toPersonResponse(Person person);

    List<PersonResponse> toPersonResponse(Collection<Person> personCollection);

}