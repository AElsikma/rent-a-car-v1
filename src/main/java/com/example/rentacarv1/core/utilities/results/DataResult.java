package com.example.rentacarv1.core.utilities.results;


import org.springframework.http.HttpStatus;

public class DataResult<T> extends Result {
    private  T data;

    public DataResult(T data, boolean success, String message, HttpStatus status) {
        super(success, status, message);
        this.data = data;
    }

    public DataResult(T data, boolean success, HttpStatus status) {
        super(success, status);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
