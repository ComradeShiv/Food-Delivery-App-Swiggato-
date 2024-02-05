package com.example.Swiggato.Exception;

public class NoCustomerExistException extends RuntimeException{

    public NoCustomerExistException(String message) {
        super(message);
    }
}
