package com.nff.objective_challenge.infra.inbound.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.nff.objective_challenge.application.core.domain.Conta;
import com.nff.objective_challenge.infra.inbound.dto.ContaDTO;
import com.nff.objective_challenge.infra.inbound.model.ContaModel;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Mappers {

    private ModelMapper modelMapper;

    public Conta contaDtoToConta(ContaDTO contaDTO) {
        return modelMapper.map(contaDTO, Conta.class);
    }

    public ContaDTO contaToContaDto(Conta conta) {
        return modelMapper.map(conta, ContaDTO.class);
    }

    public ContaModel contaToContaModel(Conta conta) {
        return modelMapper.map(conta, ContaModel.class);
    }

    public Conta contaModelToConta(ContaModel contaModel) {
        return modelMapper.map(contaModel, Conta.class);
    }
    
}
