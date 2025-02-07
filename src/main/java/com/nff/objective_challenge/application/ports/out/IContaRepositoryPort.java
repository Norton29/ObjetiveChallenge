package com.nff.objective_challenge.application.ports.out;

import com.nff.objective_challenge.application.core.domain.Conta;

public interface IContaRepositoryPort {

    Conta save(Conta conta);

    Conta buscarPorNumeroConta(Integer numeroConta);

    void update(Conta conta, Float saldo);

}
