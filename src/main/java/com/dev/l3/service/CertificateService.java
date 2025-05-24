package com.dev.l3.service;


import com.dev.l3.dto.request.CertificateRequest;
import com.dev.l3.dto.response.CertificateResponse;

import java.util.List;

public interface CertificateService {
    CertificateResponse createCertificate(Integer id, CertificateRequest request);

    List<CertificateResponse> getCertificatesByEmployeeId(Integer id);

    CertificateResponse updateCertificate(Integer certificateId, CertificateRequest request);

    void deleteCertificate(Integer certificateId);
}
