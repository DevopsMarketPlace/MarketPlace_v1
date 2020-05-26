package com.iiitb.spe.market_place_v1.Exceptions;

import org.springframework.http.HttpStatus;

public class CustomError {

    private HttpStatus httpStatus;
    private String message;

    public CustomError() {
    }

    public CustomError(HttpStatus httpStatus, String massage) {
        this.httpStatus = httpStatus;
        this.message = massage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMassage() {
        return message;
    }

    public void setMassage(String massage) {
        this.message = massage;
    }
}
