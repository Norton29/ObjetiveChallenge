package com.nff.objective_challenge.infra.inbound.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.nff.objective_challenge.application.core.domain.Conta;
import com.nff.objective_challenge.application.core.domain.Transacao;
import com.nff.objective_challenge.infra.inbound.dto.ContaDTO;
import com.nff.objective_challenge.infra.inbound.dto.TransacaoDTO;
import com.nff.objective_challenge.infra.inbound.model.ContaModel;
import com.nff.objective_challenge.infra.inbound.model.TransacaoModel;

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

    public Transacao transacaoDtoToTransacao(TransacaoDTO transacaoDTO) {
        return modelMapper.map(transacaoDTO, Transacao.class);
    }

    public TransacaoModel transacaoToTransacaoModel(Transacao transacao) {
        return modelMapper.map(transacao, TransacaoModel.class);
    }

    public Transacao transacaoModelToTransacao(TransacaoModel transacaoModel) {
        return modelMapper.map(transacaoModel, Transacao.class);
    }
    
}
