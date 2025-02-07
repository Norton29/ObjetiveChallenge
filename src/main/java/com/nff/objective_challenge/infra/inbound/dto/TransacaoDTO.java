package com.nff.objective_challenge.infra.inbound.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransacaoDTO {

    @JsonProperty("forma_pagamento")
    private String formaPagamento;

    @JsonProperty("numero_conta")
    private Integer numeroConta;

    private Float valor;

    private Float saldo;

}
