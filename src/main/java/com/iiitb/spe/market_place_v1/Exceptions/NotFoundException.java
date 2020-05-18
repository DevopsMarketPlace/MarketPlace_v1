package com.iiitb.spe.market_place_v1.Exceptions;

public class NotFoundException extends RuntimeException {

   public NotFoundException()
    {
        super();
    }
   public NotFoundException(String message)
    {
        super(message);
    }
}
