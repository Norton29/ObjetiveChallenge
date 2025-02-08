package com.nff.objectiveChallenge.infra.outbound.adapters;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nff.objectiveChallenge.application.core.domain.Conta;
import com.nff.objectiveChallenge.application.ports.out.IContaRepositoryPort;
import com.nff.objectiveChallenge.infra.config.execeptions.NotFoundException;
import com.nff.objectiveChallenge.infra.inbound.mappers.Mappers;
import com.nff.objectiveChallenge.infra.inbound.model.ContaModel;
import com.nff.objectiveChallenge.infra.outbound.repository.Contarepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ContaRepositoryAdapter implements IContaRepositoryPort {

  private Contarepository contaRepository;

  private Mappers mappers;

  @Override
  public Conta save(Conta conta) {
    return mappers.contaModelToConta(contaRepository.save(mappers.contaToContaModel(conta)));
  }

  @Override
  public Conta buscarPorNumeroConta(Integer numeroConta) {
    ContaModel contaModel = contaRepository.findByNumeroConta(numeroConta).orElseThrow(() -> new NotFoundException("Conta não encontrada"));
    return mappers.contaModelToConta(contaModel);
  }

  @Override
  public void update(Conta conta, Float saldo) {
    contaRepository.findByNumeroConta(conta.getNumeroConta()).ifPresent(contaModel -> {
      contaModel.setSaldo(saldo);
      contaRepository.save(contaModel);
    });
  }

}
