package com.nff.objectiveChallenge.application.core.usecase;

import com.nff.objectiveChallenge.application.core.domain.Conta;
import com.nff.objectiveChallenge.application.ports.in.IContaServicePort;
import com.nff.objectiveChallenge.application.ports.out.IContaRepositoryPort;

public class ContaService implements IContaServicePort {

  private final IContaRepositoryPort contaRepositoryPort;

  public ContaService(IContaRepositoryPort contaRepositoryPort) {
    this.contaRepositoryPort = contaRepositoryPort;
  }

  @Override
  public Conta createConta(Conta conta) {
    return contaRepositoryPort.save(conta);
  }

  @Override
  public Conta buscarPorNumeroConta(Integer numeroConta) {
    return contaRepositoryPort.buscarPorNumeroConta(numeroConta);
  }

}
