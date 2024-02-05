package com.example.Swiggato.Exception;

public class InvalidContactNumberException extends RuntimeException{

    public InvalidContactNumberException(String message) {
        super(message);
    }
}
