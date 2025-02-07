package com.nff.objective_challenge.application.core.domain;

import com.nff.objective_challenge.application.core.domain.enums.TipoTransacaoEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter @AllArgsConstructor
@NoArgsConstructor
public class Transacao {

    private Conta conta;

    private TipoTransacaoEnum tipoTransacao;

    private Float valor;
    
}
