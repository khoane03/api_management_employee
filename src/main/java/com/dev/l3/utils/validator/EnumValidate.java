package com.dev.l3.utils.validator;

import com.dev.l3.exception.AppException;
import com.dev.l3.exception.ErrorMess;

public class EnumValidate {

    public static <T extends Enum<T>> void enumValidate(Class<T> enumClass, String value, ErrorMess errorMess) {
        try {
            Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AppException(errorMess);
        }
    }
}
