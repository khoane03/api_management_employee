package com.dev.l3.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProcessRequest {

    @NotNull(message = "Status is required")
    String status;

    String note;

}
