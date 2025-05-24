package com.dev.l3.dto.response;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class EmployeeResponse {
    Integer id;

    String name;

    String code;

    String gender;

    String address;

    String team;

    String avatar;

    String idCard;

    String phone;

    String email;

    String status;

    String note;

    List<RelationResponse> familyRelationships;

    List<CertificateResponse> certificates;

}
