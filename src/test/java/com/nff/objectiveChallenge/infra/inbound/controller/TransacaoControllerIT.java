package com.nff.objectiveChallenge.infra.inbound.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.nff.objectiveChallenge.application.core.domain.Conta;
import com.nff.objectiveChallenge.application.core.domain.Transacao;
import com.nff.objectiveChallenge.application.core.domain.enums.TipoTransacaoEnum;
import com.nff.objectiveChallenge.application.ports.out.IContaRepositoryPort;
import com.nff.objectiveChallenge.infra.config.execeptions.BalanceException;
import com.nff.objectiveChallenge.infra.inbound.dto.TransacaoDTO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoControllerIT {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest");

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private IContaRepositoryPort contaRepository;

  @Test
  @Order(1)
  void shouldCreateTransacaoDebito() throws Exception {
    Conta conta = Conta.builder().numeroConta(123).saldo(100f).build();
    contaRepository.save(conta);
    TransacaoDTO transacao = TransacaoDTO.builder().formaPagamento("D").numeroConta(123).valor(10f).build();

    mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/transacao")
        .contentType("application/json")
        .content(new ObjectMapper().writeValueAsString(transacao)))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    Conta contaSalva = contaRepository.buscarPorNumeroConta(123);
    assertEquals(89.7f, contaSalva.getSaldo(), 0.001f);
  }

  @Test
  @Order(2)
  void shouldCreateTransacaoCredito() throws Exception {
    TransacaoDTO transacao = TransacaoDTO.builder().formaPagamento("C").numeroConta(123).valor(10f).build();

    mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/transacao")
        .contentType("application/json")
        .content(new ObjectMapper().writeValueAsString(transacao)))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    Conta contaSalva = contaRepository.buscarPorNumeroConta(123);
    assertEquals(79.2f, contaSalva.getSaldo(), 0.001f);
  }

  @Test
  @Order(3)
  void shouldCreateTransacaoPix() throws Exception {
    TransacaoDTO transacao = TransacaoDTO.builder().formaPagamento("P").numeroConta(123).valor(10f).build();

    mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/transacao")
        .contentType("application/json")
        .content(new ObjectMapper().writeValueAsString(transacao)))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    Conta contaSalva = contaRepository.buscarPorNumeroConta(123);
    assertEquals(69.2f, contaSalva.getSaldo(), 0.001f);
  }

  @Test
  @Order(4)
  void shouldNotCreateTransacaoDebitoInsuficientBilling() throws Exception {
    TransacaoDTO transacao = TransacaoDTO.builder().formaPagamento("D").numeroConta(123).valor(69.2f).build();

    mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/transacao")
        .contentType("application/json")
        .content(new ObjectMapper().writeValueAsString(transacao)))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Saldo insuficiente"));


  }

  @Test
  @Order(5)
  void shouldNotCreateTransacaoCreditoInsuficientBilling() throws Exception {
    TransacaoDTO transacao = TransacaoDTO.builder().formaPagamento("C").numeroConta(123).valor(69.2f).build();

    mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/transacao")
        .contentType("application/json")
        .content(new ObjectMapper().writeValueAsString(transacao)))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Saldo insuficiente"));


  }

  @Test
  @Order(6)
  void shouldNotCreateTransacaoPixInsuficientBilling() throws Exception {
    TransacaoDTO transacao = TransacaoDTO.builder().formaPagamento("P").numeroConta(123).valor(70f).build();

    mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/transacao")
        .contentType("application/json")
        .content(new ObjectMapper().writeValueAsString(transacao)))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Saldo insuficiente"));


  }

}
