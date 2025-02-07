package com.nff.objective_challenge.infra.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nff.objective_challenge.infra.inbound.model.ContaModel;

@Repository
public interface Contarepository extends JpaRepository<ContaModel, Long> {

    Optional<ContaModel> findByNumeroConta(Integer numeroConta);

    
    
}
