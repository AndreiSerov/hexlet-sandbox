package org.example.controller.mapper;

import org.example.controller.api.PhoneApiModel;
import org.example.controller.api.request.PersonRequest;
import org.example.controller.api.response.PersonResponse;
import org.example.domain.Person;
import org.example.domain.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author andreiserov
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class PhoneMapper {

    public abstract PhoneApiModel toResponse(Phone phone);

    public abstract Phone toModel(PhoneApiModel request);

}
