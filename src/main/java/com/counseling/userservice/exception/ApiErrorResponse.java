package com.counseling.userservice.exception;

import lombok.Data;

@Data
public class ApiErrorResponse {

    private String message;

    private String cause;
}
