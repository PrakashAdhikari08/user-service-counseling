package com.bookingservice.bookingservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleBookingNotFoundException(BookingNotFoundException e){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(e.getMessage());
        apiErrorResponse.setCause("Checked In database No order Available");
        return new ResponseEntity<ApiErrorResponse>(apiErrorResponse, HttpStatus.EXPECTATION_FAILED);
    }
}
