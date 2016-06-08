package com.aerian.bbc.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Writer not found")
public class WriterNotFoundException extends Exception {

    private static final long serialVersionUID = -3332292346834265371L;

    public WriterNotFoundException(int id){
        super("Writer not found with id "+id);
    }


}
