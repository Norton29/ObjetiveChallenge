package com.nff.objectiveChallenge.application.ports.in;

import com.nff.objectiveChallenge.application.core.domain.Transacao;

public interface ITransacaoServicePort {

    Transacao createTransacao(Transacao transacao);

}
