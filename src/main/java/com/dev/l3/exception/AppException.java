package com.dev.l3.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException  extends RuntimeException {
    private final ErrorMess errMsg;

    public AppException(ErrorMess errMsg) {
        super();
        this.errMsg = errMsg;
    }

    public AppException() {
        super();
        errMsg = null;
    }
}
