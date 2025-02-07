package com.nff.objective_challenge.application.core.usecase;

import com.nff.objective_challenge.application.core.domain.Conta;
import com.nff.objective_challenge.application.core.domain.Transacao;
import com.nff.objective_challenge.application.ports.in.ITransacaoServicePort;
import com.nff.objective_challenge.application.ports.out.IContaRepositoryPort;
import com.nff.objective_challenge.application.ports.out.ITransacaoRepositoryPort;
import com.nff.objective_challenge.infra.config.execeptions.BalanceException;
import com.nff.objective_challenge.infra.config.execeptions.GenericException;

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
    Float saldo = conta.getSaldo();
    
    switch (transacao.getFormaPagamento()) {
      case D:
      Float valorTransacaoDebito = transacao.getValor() +(transacao.getValor() * 0.03f);
      if(!permiteTransacao(conta.getSaldo(), valorTransacaoDebito)){
        throw new BalanceException("Saldo insuficiente");
      }
       saldo = saldo - valorTransacaoDebito;
        break;
      
      case C:
      Float valorTransacaoCredito = transacao.getValor() +(transacao.getValor() * 0.05f);
      if(!permiteTransacao(conta.getSaldo(), valorTransacaoCredito)){
        throw new BalanceException("Saldo insuficiente");
      }
       saldo = saldo - valorTransacaoCredito;
        break;
     
      case P:
      if(!permiteTransacao(conta.getSaldo(), transacao.getValor())){
        throw new BalanceException("Saldo insuficiente");
      }
       saldo = saldo - transacao.getValor();
        break;
      default: throw new GenericException("Tipo de transação inválido");
    }
    contaRepositoryPort.update(conta, saldo);
    transacaoRepositoryPort.save(transacao);
    return transacao;
  }


  private boolean permiteTransacao(Float saldo, Float transacao) {
    if(saldo.compareTo(transacao) < 0){
      return false;
    }else{
      return true;
    }
  }

}
