package com.nff.objectiveChallenge.application.ports.out;

import com.nff.objectiveChallenge.application.core.domain.Transacao;

public interface ITransacaoRepositoryPort {
    
    Transacao save(Transacao transacao);


}
