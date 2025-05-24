package com.dev.l3.controller;

import com.dev.l3.dto.request.RegistrationFormRequest;
import com.dev.l3.dto.response.ApiResponse;
import com.dev.l3.dto.response.RegistrationFormResponse;
import com.dev.l3.service.RegisterFormService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/registration-form")
public class RegistrationFormController {

    final RegisterFormService registerFormService;

    @PostMapping("/{id}")
    public ApiResponse<?> createRegistrationForm(@PathVariable("id") Integer id, @RequestBody RegistrationFormRequest req) {
        return ApiResponse.build(registerFormService.createForm(id, req));
    }

    @PutMapping()
    public ApiResponse<?> submitForm(@RequestParam("code") String code) {
        return ApiResponse.build(registerFormService.submitForm(code));
    }

    @PutMapping("/{code}")
    public ApiResponse<?> updateForm(@PathVariable("code") String code, @RequestBody RegistrationFormRequest req) {
        return ApiResponse.build(registerFormService.updateForm(code, req));
    }

    @GetMapping("/{code}")
    public ApiResponse<RegistrationFormResponse> getFormByCode(@PathVariable("code") String code) {
        return ApiResponse.build(registerFormService.getFormByCode(code));
    }

    @GetMapping("/employee/{code}")
    public ApiResponse<List<RegistrationFormResponse>> getFormByEmployeeCode(@PathVariable("code") String code) {
        return ApiResponse.build(registerFormService.getByEmployeeCode(code));
    }

}
