package com.nff.objectiveChallenge.infra.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nff.objectiveChallenge.application.core.usecase.ContaService;
import com.nff.objectiveChallenge.application.core.usecase.TransacaoService;
import com.nff.objectiveChallenge.application.ports.out.IContaRepositoryPort;
import com.nff.objectiveChallenge.application.ports.out.ITransacaoRepositoryPort;

@Configuration
public class Config {

  @Bean
  ContaService contaService(IContaRepositoryPort contaRepositoryPort) {
    return new ContaService(contaRepositoryPort);
  }

  @Bean
  TransacaoService transacaoService( ITransacaoRepositoryPort transacaoRepositoryPort, IContaRepositoryPort contaRepositoryPort) {
    return new TransacaoService(transacaoRepositoryPort, contaRepositoryPort);
  }

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
