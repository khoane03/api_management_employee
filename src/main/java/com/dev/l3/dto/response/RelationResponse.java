package com.dev.l3.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelationResponse {
    Integer id;

    String fullName;

    String gender;

    LocalDate dob;

    String idCard;

    String relation;

    String address;
}
