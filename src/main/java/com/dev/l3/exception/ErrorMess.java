package com.dev.l3.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorMess {

    //400
    BAD_REQUEST(400, "BAD_REQUEST", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED(400_001, "Token already expired", HttpStatus.BAD_REQUEST),
    TOKEN_INVALID(400_002, "Token is invalid", HttpStatus.BAD_REQUEST),
    TOKEN_GENERATION_FAILED(400_003, "Token generation failed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(404_001, "User not existed", HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXISTED(400_004, "User already existed", HttpStatus.BAD_REQUEST),
    CODE_ALREADY_EXISTED(400_005, "Code already existed", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCH(400_006, "Password not match", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_EXISTED(400_007, "Employee not existed", HttpStatus.BAD_REQUEST),
    GENDER_INVALID(400_008, "Gender must be male, female, other", HttpStatus.BAD_REQUEST),
    TEAM_INVALID(400_009, "Team must be DEVELOPMENT, TESTING, MARKETING, SALES, OTHER", HttpStatus.BAD_REQUEST),
    RELATION_INVALID(400_010, "Relation must be FATHER, MOTHER, SISTER, BROTHER, WIFE, HUSBAND, OTHER", HttpStatus.BAD_REQUEST),
    RELATION_NOT_EXISTED(400_011, "Relation not existed", HttpStatus.BAD_REQUEST),
    CERTIFICATE_NOT_EXISTED(400_012, "Certificate not existed", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_ALLOW_DELETE(400_013, "Employees are only allowed to delete when the status is NEW_SAVE", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_ALLOW_REQUEST_APPROVAL(400_014, "Employees are only allowed to request approval when the status is NEW_SAVE, REJECT, ADDITIONAL_REQUEST", HttpStatus.BAD_REQUEST),
    STATUS_INVALID(400_015, "Status must be APPROVED, REJECTED, ADDITIONAL_REQUIRED", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_ALLOW_UPDATE(400_016, "Employees are only allowed to update when the status is PENDING", HttpStatus.BAD_REQUEST),
    REGISTRATION_FORM_NOT_EXISTED(400_017, "Registration form not existed", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_APPROVED(400_018, "Employee must be approved to add forms", HttpStatus.BAD_REQUEST),
    REGISTRATION_FORM_NOT_ALLOW_UPDATE(400_019, "Registration form is not allowed to update when the status PENDING or APPROVED", HttpStatus.BAD_REQUEST),

    //404
    NOT_FOUND(404, "NOT_FOUND", HttpStatus.NOT_FOUND),



    UNAUTHORIZED(401, "UNAUTHORIZED", HttpStatus.UNAUTHORIZED),
    INCORRECT_PASSWORD(401_001, "Incorrect password", HttpStatus.UNAUTHORIZED),

    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);



    Integer code;
    String message;
    HttpStatus statusCode;

}