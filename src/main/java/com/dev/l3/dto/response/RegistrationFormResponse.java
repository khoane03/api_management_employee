package com.dev.l3.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationFormResponse {

    String code;

    String name;

    String content;

    String status;

    LocalDate submissionDate;

    LocalDate approvalDate;

    LocalDate rejectionDate;

    LocalDate additionalRequiredDate;

    String reason;
}
