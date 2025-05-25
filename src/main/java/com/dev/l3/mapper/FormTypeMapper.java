package com.dev.l3.mapper;

import com.dev.l3.dto.request.FormTypeRequest;
import com.dev.l3.dto.response.FormTypeResponse;
import com.dev.l3.entity.FormType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {FormTypeMapper.class})
public interface FormTypeMapper {

    FormType toEntity(FormTypeRequest request);

    FormTypeResponse toResponse(FormType entity);

    void updateFormType(@MappingTarget FormType entity, FormTypeRequest request);

}
