package com.dev.l3.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    String name;

    @NotBlank(message = "code is required")
    @Pattern(regexp = "^MN\\d+$", message = "Code must start with MN")
    String code;

    @NotBlank(message = "Position is required")
    String position;

    @NotBlank(message = "Username is required")
    @Size(min = 8, max = 20, message = "Username must be between 6 and 20 characters")
    String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one number")
    String password;

    @NotBlank(message = "Confirm Password is required")
    @Size(min = 8, max = 20, message = "Confirm password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "Confirm password must contain at least one uppercase letter, one lowercase letter, and one number")
    String confirmPassword;
}
