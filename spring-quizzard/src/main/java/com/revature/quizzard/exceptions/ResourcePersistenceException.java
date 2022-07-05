package com.revature.quizzard.exceptions;

public class ResourcePersistenceException extends QuizzardException {

    public ResourcePersistenceException() {
        super("Resource could not be persisted!");
    }

    public ResourcePersistenceException(String msg) {
        super(msg);
    }

    public ResourcePersistenceException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
