package com.nff.objectiveChallenge.application.core.domain;

import com.nff.objectiveChallenge.application.core.domain.enums.TipoTransacaoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter @AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transacao {

    private Conta conta;

    private TipoTransacaoEnum formaPagamento;

    private Float valor;
    
}
