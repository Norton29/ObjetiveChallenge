package com.nff.objective_challenge.application.ports.out;

import com.nff.objective_challenge.application.core.domain.Transacao;

public interface ITransacaoRepositoryPort {
    
    Transacao save(Transacao transacao);


}
