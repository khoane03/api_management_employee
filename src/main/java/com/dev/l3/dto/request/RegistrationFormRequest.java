package com.dev.l3.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationFormRequest {

    @NotNull(message = "Code is required")
    String code;

    @NotNull(message = "Name is required")
    String name;

    @NotNull(message = "Content is required")
    String content;

}
