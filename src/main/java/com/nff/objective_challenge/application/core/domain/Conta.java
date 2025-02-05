package com.nff.objective_challenge.application.core.domain;


public class Conta {

    private Integer numeroConta;

    private Float saldo;
    

    public Conta(Integer numeroConta, Float saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }


    public Integer getNumeroConta() {
        return numeroConta;
    }


    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }


    public Float getSaldo() {
        return saldo;
    }


    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    
}
