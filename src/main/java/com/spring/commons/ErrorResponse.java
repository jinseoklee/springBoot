package com.spring.commons;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by David on 2016-07-27.
 */
@Data
public class ErrorResponse {

    private String message;
    private String code;
    private List<FieldError> errors;

    public static class FieldError{
        private String field;
        private String value;
        private String reason;
    }



}
