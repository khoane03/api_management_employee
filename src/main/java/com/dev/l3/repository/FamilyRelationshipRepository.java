package com.dev.l3.repository;

import com.dev.l3.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, Integer> {

    @Query("SELECT f FROM FamilyRelationship f WHERE f.employeeInfo.id = ?1")
    List<FamilyRelationship> findByEmployeeId(Integer id);
}
