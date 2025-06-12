package com.dev.l3.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class EmployeeRequest {

    @NotNull(message = "Name is required")
    String name;

    @NotNull(message = "Code is required")
    String code;

    @NotNull(message = "Gender is required")
    String gender;

    @NotNull(message = "Address is required")
    String address;

    @NotNull(message = "Team is required")
    String team;

    LocalDate dob;

    String avatar;

    @NotNull(message = "Id card is required")
    @Pattern(regexp = "^[0-9]*$", message = "Id card must be a number")
    @Size(min = 12, max = 12, message = "Id card must be 12 digits")
    String idCard;

    @NotNull(message = "Phone is required")
    @Size(min = 10, message = "Phone number must be 10 digits")
    @Pattern(regexp = "^(\\+84|0)[0-9]*$",
            message = "Phone number must start with +84 or 0")
    String phone;

    @NotNull(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail.com$", message = "Email must be in the form (A-Z,a-z,0-9)@gmail.com")
    String email;
}
