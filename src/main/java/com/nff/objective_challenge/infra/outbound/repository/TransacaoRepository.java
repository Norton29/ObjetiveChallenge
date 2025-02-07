package com.nff.objective_challenge.infra.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nff.objective_challenge.infra.inbound.model.TransacaoModel;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoModel, Long> {

    
}
