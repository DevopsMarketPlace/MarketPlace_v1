package com.iiitb.spe.market_place_v1.Exceptions;

public class FoundException extends RuntimeException {

    public FoundException(){}
    public FoundException(String err)
    {
        super(err);
    }
}
