package com.nff.objective_challenge.application.ports.in;

import com.nff.objective_challenge.application.core.domain.Transacao;

public interface ITransacaoServicePort {

    Transacao createTransacao(Transacao transacao);

}
