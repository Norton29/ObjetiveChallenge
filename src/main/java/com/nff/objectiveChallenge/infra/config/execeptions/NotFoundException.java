package com.nff.objectiveChallenge.infra.config.execeptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
