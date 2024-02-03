package com.example.rentacarv1.services.constants.baseMessage;

public enum BaseMessages {

    ADD("The addition operation was successful!"),
    UPDATE("The update operation was successful!"),
    DELETE("The deletion operation was successful!"),
    GET_ALL("All records were listed!"),
    GET("The record was found!"),
    ;


    private final String message;

    BaseMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
