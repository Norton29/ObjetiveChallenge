package com.nff.objectiveChallenge.infra.inbound.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nff.objectiveChallenge.application.core.domain.Conta;
import com.nff.objectiveChallenge.application.core.domain.Transacao;
import com.nff.objectiveChallenge.application.ports.in.IContaServicePort;
import com.nff.objectiveChallenge.application.ports.in.ITransacaoServicePort;
import com.nff.objectiveChallenge.infra.inbound.dto.TransacaoDTO;
import com.nff.objectiveChallenge.infra.inbound.mappers.Mappers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/transacao")
@Tag(name = "Transacao")
public class TransacaoController {

  private final ITransacaoServicePort transacaoService;

  private final IContaServicePort contaService;

  private final Mappers mappers;

  @Operation(summary = "Criar Transação")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
      @ApiResponse(responseCode = "400", description = "Requisição inválida"),
      @ApiResponse(responseCode = "404", description = "Saldo insuficiente"),
  })
  @PostMapping
  public ResponseEntity<TransacaoDTO> createTransacao(@RequestBody TransacaoDTO transacaoDTO) {
    Transacao transacao = transacaoService.createTransacao(mappers.transacaoDtoToTransacao(transacaoDTO));
    Conta conta = contaService.buscarPorNumeroConta(transacao.getConta().getNumeroConta());
    return ResponseEntity.status(HttpStatus.CREATED).body(TransacaoDTO.builder()
        .numeroConta(transacao.getConta().getNumeroConta())
        .saldo(conta.getSaldo()).build());
  }

}
