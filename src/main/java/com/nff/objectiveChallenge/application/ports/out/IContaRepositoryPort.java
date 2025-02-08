package com.nff.objectiveChallenge.application.ports.out;

import com.nff.objectiveChallenge.application.core.domain.Conta;

public interface IContaRepositoryPort {

    Conta save(Conta conta);

    Conta buscarPorNumeroConta(Integer numeroConta);

    void update(Conta conta, Float saldo);

}
