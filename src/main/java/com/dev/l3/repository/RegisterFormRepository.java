package com.dev.l3.repository;

import com.dev.l3.entity.RegistrationForms;
import com.dev.l3.utils.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterFormRepository extends JpaRepository<RegistrationForms, Integer> {

    Optional<RegistrationForms> findByCode(String code);

    boolean existsByCode(String code);

    List<RegistrationForms> findAllByStatus(StatusEnum statusEnum);

    @Query("SELECT f FROM RegistrationForms f WHERE f.employeeInfo.code = ?1")
    List<RegistrationForms> findAllByEmployeeCode(String code);
}
