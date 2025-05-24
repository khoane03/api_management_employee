package com.dev.l3.service;

import com.dev.l3.dto.request.ProcessFormRequest;
import com.dev.l3.dto.request.RegistrationFormRequest;
import com.dev.l3.dto.response.RegistrationFormResponse;

import java.util.List;

public interface RegisterFormService {

    RegistrationFormResponse createForm(Integer id, RegistrationFormRequest req);

    RegistrationFormResponse submitForm(String code);

    RegistrationFormResponse getFormByCode(String code);

    List<RegistrationFormResponse> getAllFormsPending();

    RegistrationFormResponse reviewLeaderForm(String code, ProcessFormRequest req);

    List<RegistrationFormResponse> getByEmployeeCode(String code);

    RegistrationFormResponse updateForm(String code, RegistrationFormRequest req);
}
