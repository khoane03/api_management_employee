package com.dev.l3.mapper;

import com.dev.l3.dto.request.EmployeeRequest;
import com.dev.l3.dto.response.EmployeeResponse;
import com.dev.l3.entity.EmployeeInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CertificateMapper.class, FamilyRelationMapper.class})
public interface EmployeeMapper {

    @Mapping(target = "team", expression = "java(com.dev.l3.utils.enums.TeamEnum.valueOf(request.getTeam().toUpperCase()))")
    @Mapping(target = "gender", expression = "java(com.dev.l3.utils.enums.GenderEnum.valueOf(request.getGender().toUpperCase()))")
    EmployeeInfo toEntity(EmployeeRequest request);


    EmployeeResponse toResponse(EmployeeInfo entity);

    @Mapping(target = "team", expression = "java(com.dev.l3.utils.enums.TeamEnum.valueOf(request.getTeam().toUpperCase()))")
    @Mapping(target = "gender", expression = "java(com.dev.l3.utils.enums.GenderEnum.valueOf(request.getGender().toUpperCase()))")
    void updateEmployee(@MappingTarget EmployeeInfo employee, EmployeeRequest request);

}
