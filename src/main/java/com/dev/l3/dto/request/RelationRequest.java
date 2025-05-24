package com.dev.l3.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelationRequest {
    @NotNull(message = "Full name is required")
    String fullName;

    @NotNull(message = "Gender is required")
    String gender;

    @NotNull(message = "Date of birth is required")
    LocalDate dob;

    @NotNull(message = "Id card is required")
    @Pattern(regexp = "^[0-9]*$", message = "Id card must be a number")
    @Size(min = 12, max = 12, message = "Id card must be 12 digits")
    String idCard;

    @NotNull(message = "Relation is required")
    String relation;

    @NotNull(message = "Address is required")
    String address;
}
