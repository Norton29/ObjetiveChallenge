package com.nff.objective_challenge.infra.inbound.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nff.objective_challenge.application.core.domain.Conta;
import com.nff.objective_challenge.application.ports.in.IContaServicePort;
import com.nff.objective_challenge.infra.inbound.dto.ContaDTO;
import com.nff.objective_challenge.infra.inbound.mappers.Mappers;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/conta")
public class ContaController {

  private final IContaServicePort contaService;

  private final Mappers mappers;

  @PostMapping
  public ResponseEntity<ContaDTO> createConta(@RequestBody ContaDTO contaDto) {
    ContaDTO conta = mappers.contaToContaDto(contaService.createConta(mappers.contaDtoToConta(contaDto)));
    return ResponseEntity.status(HttpStatus.CREATED).body(conta);
  }

  @GetMapping
  public ResponseEntity<ContaDTO> getConta(@RequestParam("numero_conta") Integer numeroConta) {
    Conta conta = contaService.buscarPorNumeroConta(numeroConta);
    return ResponseEntity.ok().body(mappers.contaToContaDto(conta));
  }

}
