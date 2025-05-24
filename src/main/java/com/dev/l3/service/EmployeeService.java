package com.dev.l3.service;

import com.dev.l3.dto.request.EmployeeRequest;
import com.dev.l3.dto.request.ProcessRequest;
import com.dev.l3.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);

    EmployeeResponse getEmployeeById(Integer id);

    EmployeeResponse updateEmployee(Integer id, EmployeeRequest request);

    void deleteEmployee(Integer id);

    List<EmployeeResponse> getAllEmployees();

    void requestApproval(Integer id);

    List<EmployeeResponse> getAllPending();

    void leaderProcess(Integer id, ProcessRequest req);
}
