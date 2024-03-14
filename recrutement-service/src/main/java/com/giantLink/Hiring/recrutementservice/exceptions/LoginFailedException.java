package com.giantLink.Hiring.recrutementservice.exceptions;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException(){
        super("Username or password is incorrect");
    }
}
