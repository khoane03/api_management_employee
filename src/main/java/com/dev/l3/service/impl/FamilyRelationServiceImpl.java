package com.dev.l3.service.impl;

import com.dev.l3.dto.request.RelationRequest;
import com.dev.l3.dto.response.RelationResponse;
import com.dev.l3.entity.EmployeeInfo;
import com.dev.l3.entity.FamilyRelationship;
import com.dev.l3.exception.AppException;
import com.dev.l3.exception.ErrorMess;
import com.dev.l3.mapper.FamilyRelationMapper;
import com.dev.l3.repository.EmployeeInfoRepository;
import com.dev.l3.repository.FamilyRelationshipRepository;
import com.dev.l3.service.FamilyRelationService;
import com.dev.l3.utils.enums.GenderEnum;
import com.dev.l3.utils.enums.RelationEnum;
import com.dev.l3.utils.validator.EnumValidate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FamilyRelationServiceImpl implements FamilyRelationService {

    EmployeeInfoRepository employeeInfoRepository;
    FamilyRelationshipRepository familyRelationshipRepository;
    FamilyRelationMapper familyRelationMapper;

    @Override
    public RelationResponse createRelation(Integer id, RelationRequest request) {
        EmployeeInfo employeeInfo = employeeInfoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorMess.EMPLOYEE_NOT_EXISTED));

        EnumValidate.enumValidate(GenderEnum.class, request.getGender(), ErrorMess.GENDER_INVALID);
        EnumValidate.enumValidate(RelationEnum.class, request.getRelation(), ErrorMess.RELATION_INVALID);
        var familyRelation = familyRelationMapper.toEntity(request);
        familyRelation.setEmployeeInfo(employeeInfo);

        return familyRelationMapper.toResponse(familyRelationshipRepository.save(familyRelation));
    }

    @Override
    public List<RelationResponse> getRelationsByEmployeeId(Integer id) {
        List<FamilyRelationship> relations = familyRelationshipRepository.findByEmployeeId(id);

        return relations.stream()
                .map(familyRelationMapper::toResponse)
                .toList();
    }

    @Override
    public RelationResponse updateRelation(Integer relationId, RelationRequest request) {
        FamilyRelationship familyRelationship = familyRelationshipRepository.findById(relationId)
                .orElseThrow(() -> new AppException(ErrorMess.RELATION_NOT_EXISTED));
        familyRelationMapper.updateRelation(familyRelationship, request);

        return familyRelationMapper.toResponse(familyRelationshipRepository.save(familyRelationship));
    }

    @Override
    public void deleteRelation(Integer relationId) {
        familyRelationshipRepository.findById(relationId)
                .ifPresentOrElse(familyRelationshipRepository::delete,
                        () -> {
                            throw new AppException(ErrorMess.RELATION_NOT_EXISTED);
                        });
    }
}
