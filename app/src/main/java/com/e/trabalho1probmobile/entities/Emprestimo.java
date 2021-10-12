package com.e.trabalho1probmobile.entities;

public class Emprestimo {

    private int numeroEmprestimo;
    private Equipamento equipamento;
    private String nomePessoa;
    private String telefone;
    private String data;
    private boolean devolvido;

    public Emprestimo(Equipamento equipamento, String nomePessoa, String telefone, String data, boolean devolvido) {
        this.equipamento = equipamento;
        this.nomePessoa = nomePessoa;
        this.telefone = telefone;
        this.data = data;
        this.devolvido = devolvido;
    }

    public Emprestimo(int numeroEmprestimo, Equipamento equipamento, String nomePessoa, String telefone, String data, boolean devolvido) {
        this.numeroEmprestimo = numeroEmprestimo;
        this.equipamento = equipamento;
        this.nomePessoa = nomePessoa;
        this.telefone = telefone;
        this.data = data;
        this.devolvido = devolvido;
    }

    public Integer getNumeroEmprestimo() {
        return numeroEmprestimo;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

}
