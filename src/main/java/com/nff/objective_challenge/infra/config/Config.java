package com.nff.objective_challenge.infra.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nff.objective_challenge.application.core.usecase.ContaService;
import com.nff.objective_challenge.application.ports.out.IContaRepositoryPort;

@Configuration
public class Config {

  @Bean
  ContaService contaService(IContaRepositoryPort contaRepositoryPort) {
    return new ContaService(contaRepositoryPort);
  }

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
