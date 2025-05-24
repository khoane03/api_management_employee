package com.dev.l3.service;

import com.dev.l3.dto.request.RelationRequest;
import com.dev.l3.dto.response.RelationResponse;

import java.util.List;

public interface FamilyRelationService {
    RelationResponse createRelation(Integer id, RelationRequest request);

    List<RelationResponse> getRelationsByEmployeeId(Integer id);

    RelationResponse updateRelation(Integer relationId, RelationRequest request);

    void deleteRelation(Integer relationId);
}
