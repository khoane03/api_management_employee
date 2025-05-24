package com.dev.l3.mapper;

import com.dev.l3.dto.request.RegistrationFormRequest;
import com.dev.l3.dto.response.RegistrationFormResponse;
import com.dev.l3.entity.RegistrationForms;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RegisterFormMapper {

    RegistrationForms toEntity(RegistrationFormRequest req);

    RegistrationFormResponse toResponse(RegistrationForms entity);

    void updateForm(@MappingTarget RegistrationForms form, RegistrationFormRequest req);
}
