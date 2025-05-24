package com.dev.l3.service.impl;

import com.dev.l3.dto.request.EmployeeRequest;
import com.dev.l3.dto.request.ProcessRequest;
import com.dev.l3.dto.response.EmployeeResponse;
import com.dev.l3.entity.EmployeeInfo;
import com.dev.l3.exception.AppException;
import com.dev.l3.exception.ErrorMess;
import com.dev.l3.mapper.EmployeeMapper;
import com.dev.l3.repository.EmployeeInfoRepository;
import com.dev.l3.service.EmployeeService;
import com.dev.l3.utils.enums.GenderEnum;
import com.dev.l3.utils.enums.StatusEnum;
import com.dev.l3.utils.enums.TeamEnum;
import com.dev.l3.utils.validator.AppValidate;
import com.dev.l3.utils.validator.EnumValidate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeInfoRepository employeeInfoRepository;
    EmployeeMapper employeeMapper;


    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        AppValidate.checkDuplicate(employeeInfoRepository.existsByCode(request.getCode()), ErrorMess.CODE_ALREADY_EXISTED);
        EnumValidate.enumValidate(GenderEnum.class, request.getGender(), ErrorMess.GENDER_INVALID);
        EnumValidate.enumValidate(TeamEnum.class, request.getTeam(), ErrorMess.TEAM_INVALID);
        return employeeMapper.toResponse(employeeInfoRepository.save(employeeMapper.toEntity(request)));
    }

    @Override
    public EmployeeResponse getEmployeeById(Integer id) {
        var employee = employeeInfoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorMess.EMPLOYEE_NOT_EXISTED));
        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Integer id, EmployeeRequest request) {
        employeeInfoRepository.findById(id)
                .ifPresentOrElse(employee -> {
                    employeeMapper.updateEmployee(employee, request);
                    employeeInfoRepository.save(employee);
                }, () -> {
                    throw new AppException(ErrorMess.EMPLOYEE_NOT_EXISTED);
                });
        return getEmployeeById(id);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeInfoRepository.findById(id)
                .ifPresentOrElse(employeeInfo -> {
                            if (!employeeInfo.getStatus().equals(StatusEnum.NEW_SAVE)) {
                                throw new AppException(ErrorMess.EMPLOYEE_NOT_ALLOW_DELETE);
                            }
                            employeeInfoRepository.delete(employeeInfo);
                        },
                        () -> {
                            throw new AppException(ErrorMess.EMPLOYEE_NOT_EXISTED);
                        });

    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeInfoRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    public void requestApproval(Integer id) {
        var employee = employeeInfoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorMess.EMPLOYEE_NOT_EXISTED));
        if (employee.getStatus().equals(StatusEnum.PENDING)) {
            throw new AppException(ErrorMess.EMPLOYEE_NOT_ALLOW_REQUEST_APPROVAL);
        }
        employee.setStatus(StatusEnum.PENDING);
        employeeInfoRepository.save(employee);
    }

    @Override
    public List<EmployeeResponse> getAllPending() {
        List<EmployeeInfo> employeeInfos = employeeInfoRepository.findAllByStatus(StatusEnum.PENDING);
        return employeeInfos.stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    public void leaderProcess(Integer id, ProcessRequest req) {
        EnumValidate.enumValidate(StatusEnum.class, req.getStatus(), ErrorMess.STATUS_INVALID);
        employeeInfoRepository.findById(id)
                .ifPresentOrElse(employee -> {
                    if (!employee.getStatus().equals(StatusEnum.PENDING)) {
                        throw new AppException(ErrorMess.EMPLOYEE_NOT_ALLOW_UPDATE);
                    }
                    employee.setNote(req.getNote());
                    employee.setStatus(StatusEnum.valueOf(req.getStatus()));
                    employeeInfoRepository.save(employee);
                }, () -> {
                    throw new AppException(ErrorMess.EMPLOYEE_NOT_EXISTED);
                });
    }
}
