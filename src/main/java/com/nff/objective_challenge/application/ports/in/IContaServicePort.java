package com.nff.objective_challenge.application.ports.in;

import com.nff.objective_challenge.application.core.domain.Conta;

public interface IContaServicePort {

    public Conta createConta(Conta conta);
    
}
