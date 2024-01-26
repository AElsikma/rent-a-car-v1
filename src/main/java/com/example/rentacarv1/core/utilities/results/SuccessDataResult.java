package com.example.rentacarv1.core.utilities.results;


import org.springframework.http.HttpStatus;

public class SuccessDataResult<T> extends DataResult<T>{

    public SuccessDataResult(T data, String message, HttpStatus status) {
        super(data, true, message, status);
    }

    public SuccessDataResult(T data, HttpStatus status) {
        super(data, true, status);
    }

    public SuccessDataResult(String message, HttpStatus status) {
        super(null, true, message, status);
    }

    public SuccessDataResult(HttpStatus status) {
        super(null, true, status);
    }
}
