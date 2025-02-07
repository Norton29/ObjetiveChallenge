package com.nff.objective_challenge.infra.inbound.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nff.objective_challenge.application.core.domain.Conta;
import com.nff.objective_challenge.application.core.domain.Transacao;
import com.nff.objective_challenge.application.ports.in.IContaServicePort;
import com.nff.objective_challenge.application.ports.in.ITransacaoServicePort;
import com.nff.objective_challenge.infra.inbound.dto.TransacaoDTO;
import com.nff.objective_challenge.infra.inbound.mappers.Mappers;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/transacao")
public class TransacaoController {

    private final ITransacaoServicePort transacaoService;

    private final IContaServicePort contaService;

    private final Mappers mappers;

    @PostMapping
    public ResponseEntity<TransacaoDTO> createTransacao(@RequestBody TransacaoDTO transacaoDTO){
        Transacao transacao = transacaoService.createTransacao(mappers.transacaoDtoToTransacao(transacaoDTO));
        Conta conta = contaService.buscarPorNumeroConta(transacao.getConta().getNumeroConta());
        return ResponseEntity.status(HttpStatus.CREATED).body(TransacaoDTO.builder()
                        .numeroConta(transacao.getConta().getNumeroConta())
                        .saldo(conta.getSaldo()).build());
    }

}
