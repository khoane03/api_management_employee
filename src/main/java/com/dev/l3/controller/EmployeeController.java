package com.dev.l3.controller;

import com.dev.l3.dto.request.EmployeeRequest;
import com.dev.l3.dto.response.ApiResponse;
import com.dev.l3.dto.response.EmployeeResponse;
import com.dev.l3.service.EmployeeService;
import com.dev.l3.utils.constants.AppConst;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {

    EmployeeService employeeService;

    @PostMapping()
    public ApiResponse<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        return ApiResponse.build(employeeService.createEmployee(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<EmployeeResponse> updateEmployee(@PathVariable Integer id, @Valid @RequestBody EmployeeRequest request) {
        return ApiResponse.build(employeeService.updateEmployee(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<EmployeeResponse> getEmployeeById(@PathVariable Integer id) {
        return ApiResponse.build(employeeService.getEmployeeById(id));
    }

    @GetMapping()
    public ApiResponse<List<EmployeeResponse>> getAllEmployees() {
        return ApiResponse.build(employeeService.getAllEmployees());
    }


    @PostMapping("/request-approval/{id}")
    public ApiResponse<String> requestApproval(@PathVariable Integer id) {
        employeeService.requestApproval(id);
        return ApiResponse.build(AppConst.SUCCESS);
    }


    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ApiResponse.build(AppConst.SUCCESS);
    }

}
