package com.nff.objective_challenge.infra.outbound.adapters;

import org.springframework.stereotype.Component;

import com.nff.objective_challenge.application.core.domain.Conta;
import com.nff.objective_challenge.application.ports.out.IContaRepositoryPort;
import com.nff.objective_challenge.infra.inbound.mappers.Mappers;
import com.nff.objective_challenge.infra.outbound.repository.Contarepository;

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

}
