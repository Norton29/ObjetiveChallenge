package com.nff.objective_challenge.infra.config.execeptions;

public class NotFoundExecption extends RuntimeException {

    public NotFoundExecption(String message) {
        super(message);
    }

}
