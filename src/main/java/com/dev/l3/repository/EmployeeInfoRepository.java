package com.dev.l3.repository;

import com.dev.l3.entity.EmployeeInfo;
import com.dev.l3.entity.RegistrationForms;
import com.dev.l3.utils.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Integer> {
    boolean existsByCode(String code);

    List<EmployeeInfo> findAllByStatus(StatusEnum status);

    RegistrationForms findByCode(String code);
}
