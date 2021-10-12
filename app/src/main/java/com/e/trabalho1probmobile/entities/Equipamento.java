package com.e.trabalho1probmobile.entities;

import androidx.annotation.NonNull;

public class Equipamento {
    private int equipamentoId;
    private String nome;
    private String marca;

    public Equipamento(String nome, String marca) {
        this.nome = nome;
        this.marca = marca;
    }

    public Equipamento(int equipamentoId, String nome, String marca) {
        this.equipamentoId = equipamentoId;
        this.nome = nome;
        this.marca = marca;
    }

    public int getEquipamentoId() {
        return equipamentoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getNome() + " - " + this.getMarca();
    }
}
