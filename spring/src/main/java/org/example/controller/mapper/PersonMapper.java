package org.example.controller.mapper;

import org.example.controller.api.request.PersonRequest;
import org.example.controller.api.response.PersonResponse;
import org.example.domain.Person;
import org.example.domain.Phone;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

/**
 * @author andreiserov
 */
@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {PhoneMapper.class}
)
public abstract class PersonMapper {


    public abstract PersonResponse toResponse(Person person);

    public abstract Person toModel(PersonRequest request);

    @AfterMapping
    public void addPhoneToModel(@MappingTarget Person person, PersonRequest personRequest) {
        person.setPhones(
            personRequest
                .phone()
                .stream()
                .map(Phone::new)
                .toList()
        );
    }

    @AfterMapping
    public void addPhoneToApi(Person person, @MappingTarget PersonResponse response) {
        response.setPhone(
            person
                .getPhones()
                .stream()
                .map(Phone::getNumber)
                .toList()
        );
    }


}
