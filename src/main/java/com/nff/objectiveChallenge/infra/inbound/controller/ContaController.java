package com.nff.objectiveChallenge.infra.inbound.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nff.objectiveChallenge.application.core.domain.Conta;
import com.nff.objectiveChallenge.application.ports.in.IContaServicePort;
import com.nff.objectiveChallenge.infra.inbound.dto.ContaDTO;
import com.nff.objectiveChallenge.infra.inbound.mappers.Mappers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/conta")
@Tag(name = "Conta")
public class ContaController {

  private final IContaServicePort contaService;

  private final Mappers mappers;

  @Operation(summary = "Criar Conta")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Conta criada com sucesso"),
      @ApiResponse(responseCode = "403", description = "Conta já cadastrada"),
  })
  @PostMapping
  public ResponseEntity<ContaDTO> createConta(@RequestBody ContaDTO contaDto) {
    ContaDTO conta = mappers.contaToContaDto(contaService.createConta(mappers.contaDtoToConta(contaDto)));
    return ResponseEntity.status(HttpStatus.CREATED).body(conta);
  }

  @Operation(summary = "Buscar conta por número da conta")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Conta encontrada"),
      @ApiResponse(responseCode = "404", description = "Conta não encontrada"),
  })
  @GetMapping
  public ResponseEntity<ContaDTO> getConta(@RequestParam("numero_conta") Integer numeroConta) {
    Conta conta = contaService.buscarPorNumeroConta(numeroConta);
    return ResponseEntity.ok().body(mappers.contaToContaDto(conta));
  }

}
