package com.example.clientes.dto;

import com.example.clientes.model.Cliente;

public class ClienteDto {
    private Long id;
    private String nome;
    private Long telefone;
    private Boolean correntista;
    private float scoreCredito;
    private float saldoCc;

    public ClienteDto() {}

    public ClienteDto(Long id, String nome, Long telefone, Boolean correntista, float scoreCredito, float saldoCc) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.correntista = correntista;
        this.scoreCredito = scoreCredito;
        this.saldoCc = saldoCc;
    }

    public static ClienteDto fromEntity(Cliente cliente) {
        return new ClienteDto(
            cliente.getId(),
            cliente.getNome(),
            cliente.getTelefone(),
            cliente.getCorrentista(),
            cliente.getScoreCredito(),
            cliente.getSaldoCc()
        );
    }

    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
        cliente.setCorrentista(this.correntista);
        cliente.setScoreCredito(this.scoreCredito);
        cliente.setSaldoCc(this.saldoCc);
        return cliente;
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
