package com.dev.l3.service.impl;

import com.dev.l3.dto.request.FormTypeRequest;
import com.dev.l3.dto.response.FormTypeResponse;
import com.dev.l3.entity.User;
import com.dev.l3.exception.AppException;
import com.dev.l3.exception.ErrorMess;
import com.dev.l3.mapper.FormTypeMapper;
import com.dev.l3.repository.FormTypeRepository;
import com.dev.l3.repository.UserRepository;
import com.dev.l3.service.FormTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormTypeServiceImpl implements FormTypeService {

    final FormTypeRepository formTypeRepository;
    final FormTypeMapper formTypeMapper;
    final UserRepository userRepository;

    @Override
    public FormTypeResponse createFormType(FormTypeRequest req) {
        if (formTypeRepository.existsByCode(req.getCode())) {
            throw new AppException(ErrorMess.CODE_ALREADY_EXISTED);
        }
        var formType = formTypeMapper.toEntity(req);
        formType.setCreatedBy(getCurrent().getName());
        return formTypeMapper.toResponse(formTypeRepository.save(formType));
    }

    @Override
    public FormTypeResponse getFormTypeByCode(String code) {
        return formTypeRepository.findByCode(code)
                .map(formTypeMapper::toResponse)
                .orElseThrow(() -> new AppException(ErrorMess.FORM_TYPE_NOT_EXISTED));
    }

    @Override
    public List<FormTypeResponse> getAllFormTypes() {
        return formTypeRepository.findAll().stream()
                .map(formTypeMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteFormType(String code) {
        formTypeRepository.findByCode(code).ifPresentOrElse(
                formTypeRepository::delete,
                () -> { throw new AppException(ErrorMess.FORM_TYPE_NOT_EXISTED); }
        );
    }

    User getCurrent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated()) {
            throw new AppException(ErrorMess.UNAUTHORIZED);
        }
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }
}
