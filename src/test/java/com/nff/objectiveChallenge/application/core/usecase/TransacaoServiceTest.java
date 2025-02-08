package com.nff.objectiveChallenge.application.core.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nff.objectiveChallenge.application.core.domain.Conta;
import com.nff.objectiveChallenge.application.core.domain.Transacao;
import com.nff.objectiveChallenge.application.core.domain.enums.TipoTransacaoEnum;
import com.nff.objectiveChallenge.application.ports.out.IContaRepositoryPort;
import com.nff.objectiveChallenge.application.ports.out.ITransacaoRepositoryPort;
import com.nff.objectiveChallenge.infra.config.execeptions.BalanceException;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

  @Mock
  private ITransacaoRepositoryPort transacaoRepositoryPort;

  @Mock
  private IContaRepositoryPort contaRepositoryPort;

  @InjectMocks
  private TransacaoService transacaoService;

  @Test
  public void shouldCreateTransacaoDebito() {
    Conta conta = Conta.builder().numeroConta(111).saldo(100f).build();
    Transacao transacao = Transacao.builder()
        .conta(conta)
        .formaPagamento(TipoTransacaoEnum.D)
        .valor(10f)
        .build();

    when(contaRepositoryPort.buscarPorNumeroConta(transacao.getConta().getNumeroConta())).thenReturn(conta);
    when(transacaoRepositoryPort.save(any(Transacao.class))).thenReturn(transacao);

    transacaoService.createTransacao(transacao);

    assertEquals(89.7f, conta.getSaldo(), 0.001f);

  }

  @Test
  public void shouldCreateTransacaoCredito() {
    Conta conta = Conta.builder().numeroConta(111).saldo(100f).build();
    Transacao transacao = Transacao.builder()
        .conta(conta)
        .formaPagamento(TipoTransacaoEnum.C)
        .valor(10f)
        .build();

    when(contaRepositoryPort.buscarPorNumeroConta(transacao.getConta().getNumeroConta())).thenReturn(conta);
    when(transacaoRepositoryPort.save(any(Transacao.class))).thenReturn(transacao);

    transacaoService.createTransacao(transacao);

    assertEquals(89.5f, conta.getSaldo(), 0.001f);

  }

  @Test
  public void shouldCreateTransacaoPix() {
    Conta conta = Conta.builder().numeroConta(111).saldo(100f).build();
    Transacao transacao = Transacao.builder()
        .conta(conta)
        .formaPagamento(TipoTransacaoEnum.P)
        .valor(10f)
        .build();

    when(contaRepositoryPort.buscarPorNumeroConta(transacao.getConta().getNumeroConta())).thenReturn(conta);
    when(transacaoRepositoryPort.save(any(Transacao.class))).thenReturn(transacao);

    transacaoService.createTransacao(transacao);

    assertEquals(90f, conta.getSaldo(), 0.001f);

  }

  @Test
  public void shouldNotCreateTransacaoDebitoInsuficientBilling() {
    Conta conta = Conta.builder().numeroConta(111).saldo(100f).build();
    Transacao transacao = Transacao.builder()
        .conta(conta)
        .formaPagamento(TipoTransacaoEnum.D)
        .valor(100f)
        .build();

    when(contaRepositoryPort.buscarPorNumeroConta(transacao.getConta().getNumeroConta())).thenReturn(conta);


    BalanceException balanceException = assertThrows(BalanceException.class, () -> {
      transacaoService.createTransacao(transacao);
    });

    assertEquals("Saldo insuficiente", balanceException.getMessage());

  }

  @Test
  public void shouldNotCreateTransacaoCreditoInsuficientBilling() {
    Conta conta = Conta.builder().numeroConta(111).saldo(100f).build();
    Transacao transacao = Transacao.builder()
        .conta(conta)
        .formaPagamento(TipoTransacaoEnum.C)
        .valor(100f)
        .build();

    when(contaRepositoryPort.buscarPorNumeroConta(transacao.getConta().getNumeroConta())).thenReturn(conta);


    BalanceException balanceException = assertThrows(BalanceException.class, () -> {
      transacaoService.createTransacao(transacao);
    });

    assertEquals("Saldo insuficiente", balanceException.getMessage());

  }

  @Test
  public void shouldNotCreateTransacaoPixInsuficientBilling() {
    Conta conta = Conta.builder().numeroConta(111).saldo(100f).build();
    Transacao transacao = Transacao.builder()
        .conta(conta)
        .formaPagamento(TipoTransacaoEnum.P)
        .valor(101f)
        .build();

    when(contaRepositoryPort.buscarPorNumeroConta(transacao.getConta().getNumeroConta())).thenReturn(conta);


    BalanceException balanceException = assertThrows(BalanceException.class, () -> {
      transacaoService.createTransacao(transacao);
    });

    assertEquals("Saldo insuficiente", balanceException.getMessage());

  }

}
