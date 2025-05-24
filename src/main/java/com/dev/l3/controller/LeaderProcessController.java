package com.dev.l3.controller;

import com.dev.l3.dto.request.ProcessFormRequest;
import com.dev.l3.dto.request.ProcessRequest;
import com.dev.l3.dto.response.ApiResponse;
import com.dev.l3.dto.response.EmployeeResponse;
import com.dev.l3.dto.response.RegistrationFormResponse;
import com.dev.l3.service.EmployeeService;
import com.dev.l3.service.RegisterFormService;
import com.dev.l3.utils.constants.AppConst;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leader")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeaderProcessController {

    final EmployeeService employeeService;
    final RegisterFormService registerFormService;

    @GetMapping("/employees/pending")
    public ApiResponse<List<EmployeeResponse>> getAllPending() {
        return ApiResponse.build(employeeService.getAllPending());
    }

    @PutMapping("/{id}")
    public ApiResponse<String> approveAll(@PathVariable("id") Integer id, @RequestBody ProcessRequest req) {
        employeeService.leaderProcess(id, req);
        return ApiResponse.build(AppConst.SUCCESS);
    }

    @GetMapping("/forms/pending")
    public ApiResponse<List<RegistrationFormResponse>> getAllFormsPending() {
        return ApiResponse.build(registerFormService.getAllFormsPending());
    }

    @PutMapping("/forms/review/{code}")
    public ApiResponse<RegistrationFormResponse> reviewLeaderForm(@PathVariable("code") String code, @RequestBody ProcessFormRequest req) {
        return ApiResponse.build(registerFormService.reviewLeaderForm(code, req));
    }

    @GetMapping("/form/{code}")
    public ApiResponse<RegistrationFormResponse> getFormByCode(@PathVariable("code") String code) {
        return ApiResponse.build(registerFormService.getFormByCode(code));
    }
}
