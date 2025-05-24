package com.dev.l3.utils.validator;

import com.dev.l3.exception.AppException;
import com.dev.l3.exception.ErrorMess;

public class AppValidate {

    public static void checkDuplicate(boolean isExist, ErrorMess errorMess) {
        if (isExist) {
            throw new AppException(errorMess);
        }
    }

    public static void isMatch(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new AppException(ErrorMess.PASSWORD_NOT_MATCH);
        }
    }

    public static void isNull(Object object, ErrorMess errorMess) {
        if (object == null) {
            throw new AppException(errorMess);
        }
    }

}
