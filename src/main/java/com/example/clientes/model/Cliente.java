package com.example.clientes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;
    private Long telefone;
    private Boolean correntista;
    private float scoreCredito;
    private float saldoCc;

    public Cliente() {
    }

    public Cliente(String nome, Long telefone, Boolean correntista, float scoreCredito, float saldoCc) {
        this.nome = nome;
        this.telefone = telefone;
        this.correntista = correntista;
        this.scoreCredito = scoreCredito;
        this.saldoCc = saldoCc;
    }

    public Cliente(long l, String joãoSilva, String mail) {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Long getTelefone() {
        return telefone;
    }
    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }
    public Boolean getCorrentista() {
        return correntista;
    }
    public void setCorrentista(Boolean correntista) {
        this.correntista = correntista;
    }
    public float getScoreCredito() {
        return scoreCredito;
    }
    public void setScoreCredito(float scoreCredito) {
        this.scoreCredito = scoreCredito;
    }
    public float getSaldoCc() {
        return saldoCc;
    }
    public void setSaldoCc(float saldoCc) {
        this.saldoCc = saldoCc;
    }
}