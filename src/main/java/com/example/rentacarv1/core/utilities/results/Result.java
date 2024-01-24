package com.example.rentacarv1.core.utilities.results;


import org.springframework.http.HttpStatus;

public class Result {

    private  boolean success;
    private  String message;

    private HttpStatus httpStatus;

    public Result(boolean success, HttpStatus httpStatus) {
        this.success = success;
        this.httpStatus = httpStatus;
    }

    public Result(boolean success, HttpStatus httpStatus, String message) {
        this(success, httpStatus);
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}
