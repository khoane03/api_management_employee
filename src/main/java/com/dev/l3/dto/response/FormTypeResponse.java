package com.dev.l3.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormTypeResponse {

    String code;

    String name;

    String createdBy;
}
