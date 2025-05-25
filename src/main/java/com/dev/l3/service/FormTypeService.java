package com.dev.l3.service;

import com.dev.l3.dto.request.FormTypeRequest;
import com.dev.l3.dto.response.FormTypeResponse;

import java.util.List;

public interface FormTypeService {
    FormTypeResponse createFormType(FormTypeRequest req);

    FormTypeResponse getFormTypeByCode(String code);

    List<FormTypeResponse> getAllFormTypes();

    void deleteFormType(String code);
}
