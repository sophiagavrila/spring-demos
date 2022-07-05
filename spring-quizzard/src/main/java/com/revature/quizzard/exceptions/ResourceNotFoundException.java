package com.revature.quizzard.exceptions;

public class ResourceNotFoundException extends QuizzardException {

    public ResourceNotFoundException() {
        super("No resource found with the provided search criteria!");
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

}
