package com.dev.l3.mapper;

import com.dev.l3.dto.request.CertificateRequest;
import com.dev.l3.dto.response.CertificateResponse;
import com.dev.l3.entity.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    @Mapping(target = "name", source = "certificateName")
    Certificate toEntity(CertificateRequest request);

    @Mapping(target = "certificateName", source = "name")
    CertificateResponse toResponse(Certificate entity);

    @Mapping(target = "name", source = "certificateName")
    void updateCertificate(@MappingTarget Certificate certificate, CertificateRequest request);
}
