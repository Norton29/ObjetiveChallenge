package com.nff.objectiveChallenge.infra.inbound.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nff.objectiveChallenge.application.core.domain.Conta;
import com.nff.objectiveChallenge.application.ports.out.IContaRepositoryPort;
import com.nff.objectiveChallenge.infra.config.execeptions.NotFoundException;
import com.nff.objectiveChallenge.infra.inbound.dto.ContaDTO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class ContaControllerIT {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest");

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private IContaRepositoryPort contaRepository;

  @Test
  @Order(1)
  public void shouldCreateConta() throws Exception {
    ContaDTO conta = ContaDTO.builder().numeroConta(222).saldo(100f).build();

    mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/conta")
        .contentType("application/json")
        .content(new ObjectMapper().writeValueAsString(conta)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.numero_conta").value(222))
        .andExpect(MockMvcResultMatchers.jsonPath("$.saldo").value(100f));

    Conta contaSalva = contaRepository.buscarPorNumeroConta(222);
    assertEquals(100f, contaSalva.getSaldo(), 0.001f);

  }

  @Test
  @Order(2)
  public void shouldNotCreateContaAlreadyExist() throws Exception {
    ContaDTO conta = ContaDTO.builder().numeroConta(222).saldo(100f).build();

    mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/conta")
        .contentType("application/json")
        .content(new ObjectMapper().writeValueAsString(conta)))
        .andExpect(MockMvcResultMatchers.status().isForbidden());

  }

  @Test
  @Order(3)
  public void shouldFindConta() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/conta?numero_conta=222"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.numero_conta").value(222))
        .andExpect(MockMvcResultMatchers.jsonPath("$.saldo").value(100f));

  }

  @Test
  @Order(4)
  public void shouldNotFindConta() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/conta?numero_conta=111"))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Conta não encontrada"));

  }

}
