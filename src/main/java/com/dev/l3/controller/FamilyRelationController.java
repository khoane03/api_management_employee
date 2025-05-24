package com.dev.l3.controller;

import com.dev.l3.dto.request.RelationRequest;
import com.dev.l3.dto.response.ApiResponse;
import com.dev.l3.dto.response.RelationResponse;
import com.dev.l3.service.FamilyRelationService;
import com.dev.l3.utils.constants.AppConst;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/family")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FamilyRelationController {
    FamilyRelationService familyRelationService;

    @PostMapping("/{id}")
    public ApiResponse<RelationResponse> create(@PathVariable Integer id, @RequestBody RelationRequest request) {
        return ApiResponse.build(familyRelationService.createRelation(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<List<RelationResponse>> getRelationsByEmployeeId(@PathVariable Integer id) {
        return ApiResponse.build(familyRelationService.getRelationsByEmployeeId(id));
    }

    @PutMapping("/{relationId}")
    public ApiResponse<RelationResponse> updateRelation(@PathVariable Integer relationId, @RequestBody RelationRequest request) {
        return ApiResponse.build(familyRelationService.updateRelation(relationId, request));
    }

    @DeleteMapping("/{relationId}")
    public ApiResponse<String> deleteRelation(@PathVariable Integer relationId) {
        familyRelationService.deleteRelation(relationId);
        return ApiResponse.build(AppConst.SUCCESS);
    }
}
