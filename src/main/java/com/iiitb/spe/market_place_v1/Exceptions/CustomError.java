package com.iiitb.spe.market_place_v1.Exceptions;

import org.springframework.http.HttpStatus;

public class CustomError {

    private HttpStatus httpStatus;
    private String massage;

    public CustomError() {
    }

    public CustomError(HttpStatus httpStatus, String massage) {
        this.httpStatus = httpStatus;
        this.massage = massage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
