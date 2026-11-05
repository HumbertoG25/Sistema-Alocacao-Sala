package com.Sistema_SLA.demo.service;

public class BancoDadosException extends RuntimeException {

    public BancoDadosException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
