package com.example.rentacarv1.core.utilities.results;


import org.springframework.http.HttpStatus;

public class ErrorResult extends Result{

    public ErrorResult(HttpStatus status) {
        super(false, status);
    }

    public ErrorResult(HttpStatus status, String message) {
        super(false, status, message);
    }
}
