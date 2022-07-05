package com.revature.quizzard.exceptions;

public class InvalidRequestException extends QuizzardException {

    public InvalidRequestException() {
        super("An invalid request was made!");
    }

    public InvalidRequestException(String msg) {
        super(msg);
    }

}
