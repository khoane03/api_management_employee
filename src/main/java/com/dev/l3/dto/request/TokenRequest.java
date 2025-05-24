package com.dev.l3.dto.request;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class TokenRequest {
    String token;
}
