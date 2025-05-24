package com.dev.l3.dto.response;

import com.dev.l3.exception.ErrorMess;
import com.dev.l3.utils.constants.AppConst;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApiResponse<T> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConst.DATE_FORMAT)
    final LocalDateTime timestamp = LocalDateTime.now();
    int code;
    String message;
    String ErrorMess;
    T data;

    public static <T> ApiResponse<T> build(T data){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(data);
        apiResponse.setMessage(AppConst.SUCCESS);
        return apiResponse;
    }

    public static <T> ApiResponse<T> buildException(ErrorMess err){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(err.getStatusCode().value());
        apiResponse.setMessage(err.getMessage());
        return apiResponse;
    }

    public static <T> ApiResponse<T> buildException(String err, HttpStatus httpStatus){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(httpStatus.value());
        apiResponse.setMessage(err);
        return apiResponse;
    }
}
