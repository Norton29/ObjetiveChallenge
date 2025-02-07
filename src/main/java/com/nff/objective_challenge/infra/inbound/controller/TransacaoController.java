package com.nff.objective_challenge.infra.inbound.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nff.objective_challenge.application.core.domain.Transacao;
import com.nff.objective_challenge.application.ports.in.ITransacaoServicePort;
import com.nff.objective_challenge.infra.inbound.dto.TransacaoDTO;
import com.nff.objective_challenge.infra.inbound.mappers.Mappers;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/transacao")
public class TransacaoController {

    private final ITransacaoServicePort transacaoService;

    private final Mappers mappers;

    @PostMapping
    public ResponseEntity<TransacaoDTO> createTransacao(@RequestBody TransacaoDTO transacaoDTO){
        Transacao transacao = transacaoService.createTransacao(mappers.transacaoDtoToTransacao(transacaoDTO));
        return ResponseEntity.ok().body(TransacaoDTO.builder()
                        .numeroConta(transacao.getConta().getNumeroConta())
                        .saldo(transacao.getConta().getSaldo()).build());
    }

}
