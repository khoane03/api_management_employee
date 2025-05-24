package com.dev.l3.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateRequest {

    @NotNull(message = "Employee id is required")
    String certificateName;

    @NotNull(message = "Issued date is required")
    LocalDate issuedDate;

    @NotNull(message = "Content is required")
    String content;

    @NotNull(message = "Field is required")
    String field;
}
