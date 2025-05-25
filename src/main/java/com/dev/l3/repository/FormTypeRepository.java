package com.dev.l3.repository;

import com.dev.l3.entity.FormType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormTypeRepository extends JpaRepository<FormType, Integer> {

    Optional<FormType> findByCode(String code);

    boolean existsByCode(String code);
}
