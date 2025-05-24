package com.dev.l3.mapper;

import com.dev.l3.dto.request.RelationRequest;
import com.dev.l3.dto.response.RelationResponse;
import com.dev.l3.entity.FamilyRelationship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FamilyRelationMapper {

    @Mapping(target = "gender", expression = "java(com.dev.l3.utils.enums.GenderEnum.valueOf(request.getGender().toUpperCase()))")
    @Mapping(target = "relation", expression = "java(com.dev.l3.utils.enums.RelationEnum.valueOf(request.getRelation().toUpperCase()))")
    FamilyRelationship toEntity(RelationRequest request);

    RelationResponse toResponse(FamilyRelationship entity);

    @Mapping(target = "gender", expression = "java(com.dev.l3.utils.enums.GenderEnum.valueOf(request.getGender().toUpperCase()))")
    @Mapping(target = "relation", expression = "java(com.dev.l3.utils.enums.RelationEnum.valueOf(request.getRelation().toUpperCase()))")
    void updateRelation(@MappingTarget FamilyRelationship relation, RelationRequest request);

}
