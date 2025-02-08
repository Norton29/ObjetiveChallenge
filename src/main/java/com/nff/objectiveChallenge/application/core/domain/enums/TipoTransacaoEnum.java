package com.nff.objectiveChallenge.application.core.domain.enums;

public enum TipoTransacaoEnum {

  D("Cartão de Débito"),
  C("Cartão de Crédito"),
  P("Pix"),
  ;

  TipoTransacaoEnum(String descricao) {
    this.descricao = descricao;
  }

  private String descricao;

  public String getDescricao() {
    return descricao;
  }
}
