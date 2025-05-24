package com.dev.l3.controller;

import com.dev.l3.dto.request.CertificateRequest;
import com.dev.l3.dto.response.ApiResponse;
import com.dev.l3.dto.response.CertificateResponse;
import com.dev.l3.service.CertificateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/certificate")
public class CertificateController {

    final CertificateService certificateService;

    @PostMapping("/{id}")
    public ApiResponse<CertificateResponse> createCertificate(@PathVariable("id") Integer id, @RequestBody CertificateRequest req) {
        return ApiResponse.build(certificateService.createCertificate(id, req));
    }

    @GetMapping("/{id}")
    public ApiResponse<List<CertificateResponse>> getCertificatesByEmployeeId(@PathVariable("id") Integer id) {
        return ApiResponse.build(certificateService.getCertificatesByEmployeeId(id));
    }

    @PutMapping("/{certificateId}")
    public ApiResponse<CertificateResponse> updateCertificate(@PathVariable("certificateId") Integer certificateId, @RequestBody CertificateRequest req) {
        return ApiResponse.build(certificateService.updateCertificate(certificateId, req));
    }

    @DeleteMapping("/{certificateId}")
    public ApiResponse<String> deleteCertificate(@PathVariable("certificateId") Integer certificateId) {
        certificateService.deleteCertificate(certificateId);
        return ApiResponse.build("Certificate deleted successfully");
    }



}
