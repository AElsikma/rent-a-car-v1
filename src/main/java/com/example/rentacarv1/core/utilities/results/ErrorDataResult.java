package com.example.rentacarv1.core.utilities.results;


import org.springframework.http.HttpStatus;

public class ErrorDataResult<T> extends DataResult<T>{

    public ErrorDataResult(T data, String message, HttpStatus status) {
        super(data, false, message, status);
    }

    public ErrorDataResult(T data, HttpStatus status) {
        super(data, false, status);
    }

    public ErrorDataResult(String message, HttpStatus status) {
        super(null, false, message, status);
    }

    public ErrorDataResult(HttpStatus status) {
        super(null, false, status);
    }
}
