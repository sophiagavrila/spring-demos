package com.revature.quizzard.exceptions;

public class AuthorizationException extends QuizzardException {

    public AuthorizationException() {
        super("Your role does not allow you to perform this action!");
    }

    public AuthorizationException(String msg) {
        super(msg);
    }
}
