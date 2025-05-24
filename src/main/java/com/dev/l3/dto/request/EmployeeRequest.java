package com.dev.l3.dto.request;

import jakarta.validation.constraints.NotNull;
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
    String idCard;

    @NotNull(message = "Phone is required")
    @Size(min = 10, message = "Phone number must be 10 digits")
    String phone;

    @NotNull(message = "Email is required")
    String email;
}
