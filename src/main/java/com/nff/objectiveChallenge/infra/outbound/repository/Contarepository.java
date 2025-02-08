package com.nff.objectiveChallenge.infra.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nff.objectiveChallenge.infra.inbound.model.ContaModel;

@Repository
public interface Contarepository extends JpaRepository<ContaModel, Long> {

    Optional<ContaModel> findByNumeroConta(Integer numeroConta);

    
    
}
