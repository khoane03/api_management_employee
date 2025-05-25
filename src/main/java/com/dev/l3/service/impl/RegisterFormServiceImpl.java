package com.dev.l3.service.impl;

import com.dev.l3.dto.request.ProcessFormRequest;
import com.dev.l3.dto.request.RegistrationFormRequest;
import com.dev.l3.dto.response.RegistrationFormResponse;
import com.dev.l3.entity.EmployeeInfo;
import com.dev.l3.entity.User;
import com.dev.l3.exception.AppException;
import com.dev.l3.exception.ErrorMess;
import com.dev.l3.mapper.RegisterFormMapper;
import com.dev.l3.repository.EmployeeInfoRepository;
import com.dev.l3.repository.FormTypeRepository;
import com.dev.l3.repository.RegisterFormRepository;
import com.dev.l3.repository.UserRepository;
import com.dev.l3.service.RegisterFormService;
import com.dev.l3.utils.enums.StatusEnum;
import com.dev.l3.utils.validator.AppValidate;
import com.dev.l3.utils.validator.EnumValidate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class RegisterFormServiceImpl implements RegisterFormService {

    final RegisterFormRepository registerFormRepository;
    final UserRepository userRepository;
    final EmployeeInfoRepository employeeInfoRepository;
    final RegisterFormMapper registerFormMapper;
    final FormTypeRepository formTypeRepository;

    @Override
    public RegistrationFormResponse createForm(Integer employeeId, RegistrationFormRequest req) {
        AppValidate.checkDuplicate(registerFormRepository.existsByCode(req.getCode()), ErrorMess.CODE_ALREADY_EXISTED);
        var employee = getEmployee(employeeId);
        if (!employee.getStatus().equals(StatusEnum.APPROVED)) {
            throw new AppException(ErrorMess.EMPLOYEE_NOT_APPROVED);
        }
        var registrationForm = registerFormMapper.toEntity(req);
        registrationForm.setEmployeeInfo(employee);
        registrationForm.setManager(getCurrent());
        registrationForm.setFormType(formTypeRepository.findByCode(req.getFormCode())
                .orElseThrow(() -> new AppException(ErrorMess.FORM_TYPE_NOT_EXISTED)));
        registrationForm.setStatus(StatusEnum.NEW_SAVE);
        return registerFormMapper.toResponse(registerFormRepository.save(registrationForm));
    }

    @Override
    public RegistrationFormResponse submitForm(String code) {
        var registrationForm = registerFormRepository.findByCode(code)
                .orElseThrow(() -> new AppException(ErrorMess.REGISTRATION_FORM_NOT_EXISTED));
        registrationForm.setStatus(StatusEnum.PENDING);
        registrationForm.setSubmissionDate(LocalDate.now());
        return registerFormMapper.toResponse(registerFormRepository.save(registrationForm));
    }

    @Override
    public RegistrationFormResponse getFormByCode(String code) {
        var registrationForm = registerFormRepository.findByCode(code)
                .orElseThrow(() -> new AppException(ErrorMess.REGISTRATION_FORM_NOT_EXISTED));
        return registerFormMapper.toResponse(registrationForm);
    }

    @Override
    public List<RegistrationFormResponse> getByEmployeeCode(String code) {
        return registerFormRepository.findAllByEmployeeCode(code).stream()
                .map(registerFormMapper::toResponse)
                .toList();
    }

    @Override
    public List<RegistrationFormResponse> getAllFormsPending() {
        return registerFormRepository.findAllByStatus(StatusEnum.PENDING).stream()
                .map(registerFormMapper::toResponse)
                .toList();
    }

    @Override
    public RegistrationFormResponse reviewLeaderForm(String code, ProcessFormRequest req) {
        EnumValidate.enumValidate(StatusEnum.class, req.getStatus(), ErrorMess.STATUS_INVALID);
        var registrationForm = registerFormRepository.findByCode(code)
                .orElseThrow(() -> new AppException(ErrorMess.REGISTRATION_FORM_NOT_EXISTED));
        registrationForm.setLeader(getCurrent());
        registrationForm.setPositionApproval(getCurrent().getPosition());
        registrationForm.setReason(req.getReason());

        switch (StatusEnum.valueOf(req.getStatus())) {

            case APPROVED -> {
                registrationForm.setStatus(StatusEnum.APPROVED);
                registrationForm.setApprovalDate(LocalDate.now());
            }

            case REJECTED -> {
                registrationForm.setStatus(StatusEnum.REJECTED);
                registrationForm.setRejectionDate(LocalDate.now());
            }

            case ADDITIONAL_REQUIRED -> {
                registrationForm.setStatus(StatusEnum.ADDITIONAL_REQUIRED);
                registrationForm.setAdditionalRequiredDate(LocalDate.now());
            }

            default -> throw new AppException(ErrorMess.STATUS_INVALID);
        }
        return registerFormMapper.toResponse(registerFormRepository.save(registrationForm));
    }

    @Override
    public RegistrationFormResponse updateForm(String code, RegistrationFormRequest req) {
        var registrationForm = registerFormRepository.findByCode(code)
                .orElseThrow(() -> new AppException(ErrorMess.REGISTRATION_FORM_NOT_EXISTED));
        if (registrationForm.getStatus().equals(StatusEnum.PENDING) || registrationForm.getStatus().equals(StatusEnum.APPROVED)) {
            throw new AppException(ErrorMess.REGISTRATION_FORM_NOT_ALLOW_UPDATE);
        }
        registerFormMapper.updateForm(registrationForm, req);
        registrationForm.setSubmissionDate(LocalDate.now());
        registrationForm.setStatus(StatusEnum.PENDING);
        registrationForm.setReason(null);
        return registerFormMapper.toResponse(registerFormRepository.save(registrationForm));
    }

    User getCurrent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated()) {
            throw new AppException(ErrorMess.UNAUTHORIZED);
        }
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    EmployeeInfo getEmployee(Integer id) {
        return employeeInfoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorMess.EMPLOYEE_NOT_EXISTED));

    }
}
