package com.dev.l3.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateResponse {

    Integer id;

    String certificateName;

    LocalDate issuedDate;

    String content;

    String field;
}
