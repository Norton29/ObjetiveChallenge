package com.nff.objective_challenge.application.core.usecase;

import com.nff.objective_challenge.application.core.domain.Conta;
import com.nff.objective_challenge.application.core.domain.Transacao;
import com.nff.objective_challenge.application.ports.in.ITransacaoServicePort;
import com.nff.objective_challenge.application.ports.out.IContaRepositoryPort;
import com.nff.objective_challenge.application.ports.out.ITransacaoRepositoryPort;

public class TransacaoService implements ITransacaoServicePort {

  private final ITransacaoRepositoryPort transacaoRepositoryPort;

  private final IContaRepositoryPort contaRepositoryPort;

  public TransacaoService(ITransacaoRepositoryPort transacaoRepository, IContaRepositoryPort contaRepository) {
    this.transacaoRepositoryPort = transacaoRepository;
    this.contaRepositoryPort = contaRepository;
  }

  @Override
  public Transacao createTransacao(Transacao transacao) {
    Conta conta = contaRepositoryPort.buscarPorNumeroConta(transacao.getConta().getNumeroConta());

    if(!permiteTransacao(conta, transacao)){
      throw new RuntimeException("Saldo insuficiente");
    }
    switch (transacao.getTipoTransacao()) {
      case D:


        break;
      case C:

        break;
      case P:

        break;
      default:
        break;
    }

  }


  private boolean permiteTransacao(Conta conta, Transacao transacao) {
    if(conta.getSaldo().compareTo(transacao.getValor()) < 0){
      return false;
    }else{
      return true;
    }
  }

}
