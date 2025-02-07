package com.nff.objective_challenge.infra.outbound.adapters;

import org.springframework.stereotype.Component;

import com.nff.objective_challenge.application.core.domain.Transacao;
import com.nff.objective_challenge.application.ports.out.ITransacaoRepositoryPort;
import com.nff.objective_challenge.infra.inbound.mappers.Mappers;
import com.nff.objective_challenge.infra.inbound.model.TransacaoModel;
import com.nff.objective_challenge.infra.outbound.repository.TransacaoRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TransacaoRepositoryAdapter implements ITransacaoRepositoryPort {

  private final TransacaoRepository transacaoRepository;

  private final Mappers mappers;

  @Override
  public Transacao save(Transacao transacao) {
    TransacaoModel transacaoModel = transacaoRepository.save(mappers.transacaoToTransacaoModel(transacao));
    return mappers.transacaoModelToTransacao(transacaoModel);
  }

}
