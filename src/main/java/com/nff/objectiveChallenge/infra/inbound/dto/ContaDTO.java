package com.nff.objectiveChallenge.infra.inbound.dto;

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
public class ContaDTO {
    
    @JsonProperty("numero_conta")
    private Integer numeroConta;
    
    private Float saldo;
    
}
