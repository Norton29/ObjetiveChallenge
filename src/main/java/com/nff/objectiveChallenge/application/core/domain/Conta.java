package com.nff.objectiveChallenge.application.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conta {

    private Integer numeroConta;

    private Float saldo;

    
}
