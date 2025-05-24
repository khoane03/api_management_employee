package com.dev.l3.repository;

import com.dev.l3.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

    @Query("SELECT c FROM Certificate c WHERE c.employeeInfo.id = ?1")
    List<Certificate> findByEmployeeId(Integer id);
}
