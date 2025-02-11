package com.nff.objectiveChallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "BancoObjective", version = "1", description = "API desenvolvida como desafio para a Objective"))
public class ObjectiveChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObjectiveChallengeApplication.class, args);
	}

}
