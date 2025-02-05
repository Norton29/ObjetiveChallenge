package com.nff.objectiveChallenge;

import org.springframework.boot.SpringApplication;

import com.nff.objective_challenge.ObjectiveChallengeApplication;

public class TestObjectiveChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.from(ObjectiveChallengeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
