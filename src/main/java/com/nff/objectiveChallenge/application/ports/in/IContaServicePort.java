package com.nff.objectiveChallenge.application.ports.in;

import com.nff.objectiveChallenge.application.core.domain.Conta;

public interface IContaServicePort {

    public Conta createConta(Conta conta);

    public Conta buscarPorNumeroConta(Integer numeroConta);
    
}
