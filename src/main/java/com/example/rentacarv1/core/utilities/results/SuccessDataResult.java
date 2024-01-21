package com.example.rentacarv1.core.utilities.results;

import java.io.Serial;
import java.io.Serializable;

public class SuccessDataResult<T> extends DataResult<T> implements Serializable{
    @Serial
    private static final long serialVersionUID = 8205633081840468799L;


    public SuccessDataResult(T data, String message) {

        super(data, true,message);
    }

    public SuccessDataResult(T data){
        super(data,true);
    }

    public SuccessDataResult( String message) {

        super(null, true,message);
    }

    public SuccessDataResult() {
        super(null,true);
    }
}
