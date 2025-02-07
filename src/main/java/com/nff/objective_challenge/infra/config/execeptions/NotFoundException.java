package com.nff.objective_challenge.infra.config.execeptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
