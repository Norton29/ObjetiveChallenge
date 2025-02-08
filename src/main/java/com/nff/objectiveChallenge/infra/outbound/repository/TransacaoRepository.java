package com.nff.objectiveChallenge.infra.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nff.objectiveChallenge.infra.inbound.model.TransacaoModel;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoModel, Long> {

    
}
