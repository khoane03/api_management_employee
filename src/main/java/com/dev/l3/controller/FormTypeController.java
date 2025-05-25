package com.dev.l3.controller;

import com.dev.l3.dto.request.FormTypeRequest;
import com.dev.l3.dto.response.ApiResponse;
import com.dev.l3.dto.response.FormTypeResponse;
import com.dev.l3.service.FormTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/form-type")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormTypeController {

    FormTypeService formTypeService;

    @PostMapping()
    public ApiResponse<FormTypeResponse> createFormType(@RequestBody FormTypeRequest req) {
        return ApiResponse.build(formTypeService.createFormType(req));
    }

    @GetMapping("/{code}")
    public ApiResponse<FormTypeResponse> getFormTypeByCode(@PathVariable String code) {
        return ApiResponse.build(formTypeService.getFormTypeByCode(code));
    }

    @GetMapping()
    public ApiResponse<List<FormTypeResponse>> getAllFormTypes() {
        return ApiResponse.build(formTypeService.getAllFormTypes());
    }

    @DeleteMapping("/{code}")
    public ApiResponse<String> deleteFormType(@PathVariable String code) {
        formTypeService.deleteFormType(code);
        return ApiResponse.build("Form type deleted successfully");
    }

}
