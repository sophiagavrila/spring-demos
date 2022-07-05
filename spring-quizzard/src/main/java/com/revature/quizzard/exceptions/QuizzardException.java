package com.revature.quizzard.exceptions;

public class QuizzardException extends RuntimeException {

    public QuizzardException(String msg) {
        super(msg);
    }

    public QuizzardException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public QuizzardException(Throwable cause) {
        super(cause);
    }

}
