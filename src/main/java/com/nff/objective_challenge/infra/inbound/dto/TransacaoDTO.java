package com.nff.objective_challenge.infra.inbound.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransacaoDTO {

    private String formaPagamento;

    private Integer numeroConta;

    private Float valor;

    private Float saldo;

}
