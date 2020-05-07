package com.iiitb.spe.market_place_v1.Exceptions;

import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CentralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> Resourcenotfound(NotFoundException e,WebRequest request)
    {
        ExceptionResponse err=new ExceptionResponse(e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> anyOtherException(Exception e, WebRequest request)
    {

        System.out.println("2nd");
        e.printStackTrace();
        ExceptionResponse err=new ExceptionResponse(e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }

}
