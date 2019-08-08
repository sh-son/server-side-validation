package com.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidCustomException extends RuntimeException {
    private Error[] errors;

    public ValidCustomException(String defaultMessage, String field){
        this.errors = new Error[] {new Error(defaultMessage, field)};
    }

    public ValidCustomException(Error[] errors) {
        this.errors = errors;
    }

    public Error[] getErrors() {
        return errors;
    }

    @AllArgsConstructor
    @Getter
    public static class Error {
        private String defaultMessage;
        private String field;
    }

}
