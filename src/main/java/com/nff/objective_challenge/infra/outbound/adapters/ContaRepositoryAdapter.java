package com.nff.objective_challenge.infra.outbound.adapters;

import org.springframework.stereotype.Component;

import com.nff.objective_challenge.application.core.domain.Conta;
import com.nff.objective_challenge.application.ports.out.IContaRepositoryPort;
import com.nff.objective_challenge.infra.config.execeptions.NotFoundExecption;
import com.nff.objective_challenge.infra.inbound.mappers.Mappers;
import com.nff.objective_challenge.infra.inbound.model.ContaModel;
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

  @Override
  public Conta buscarPorNumeroConta(Integer numeroConta) {
    ContaModel contaModel = contaRepository.findByNumeroConta(numeroConta);
    if(contaModel == null){
      throw new NotFoundExecption("Conta não encontrada");
    }
    return mappers.contaModelToConta(contaModel);
  }

}
