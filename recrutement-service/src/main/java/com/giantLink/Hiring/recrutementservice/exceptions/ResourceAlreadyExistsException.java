package com.giantLink.Hiring.recrutementservice.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}