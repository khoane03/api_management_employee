package com.dev.l3.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormTypeRequest {

    @NotNull(message = "Code is required")
    String code;

    @NotNull(message = "Name is required")
    String name;

}
