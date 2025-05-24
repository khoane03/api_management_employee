package com.dev.l3.service.impl;

import com.dev.l3.dto.request.CertificateRequest;
import com.dev.l3.dto.response.CertificateResponse;
import com.dev.l3.exception.AppException;
import com.dev.l3.exception.ErrorMess;
import com.dev.l3.mapper.CertificateMapper;
import com.dev.l3.repository.CertificateRepository;
import com.dev.l3.repository.EmployeeInfoRepository;
import com.dev.l3.service.CertificateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateServiceImpl implements CertificateService {

    final CertificateRepository certificateRepository;
    final EmployeeInfoRepository employeeInfoRepository;
    final CertificateMapper certificateMapper;

    @Override
    public CertificateResponse createCertificate(Integer id, CertificateRequest request) {
        var employee = employeeInfoRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorMess.USER_NOT_EXISTED));
        var certificate = certificateMapper.toEntity(request);
        certificate.setEmployeeInfo(employee);
        return certificateMapper.toResponse(certificateRepository.save(certificate));
    }

    @Override
    public List<CertificateResponse> getCertificatesByEmployeeId(Integer id) {
        return certificateRepository.findByEmployeeId(id).stream()
                .map(certificateMapper::toResponse)
                .toList();
    }

    @Override
    public CertificateResponse updateCertificate(Integer certificateId, CertificateRequest request) {
        var certificate = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new AppException(ErrorMess.CERTIFICATE_NOT_EXISTED));
        certificateMapper.updateCertificate(certificate, request);
        certificateRepository.save(certificate);
       return certificateMapper.toResponse(certificate);
    }

    @Override
    public void deleteCertificate(Integer certificateId) {
        certificateRepository.findById(certificateId)
                .ifPresentOrElse(certificateRepository::delete,
                        () -> {
                            throw new AppException(ErrorMess.CERTIFICATE_NOT_EXISTED);
                        });
    }
}
