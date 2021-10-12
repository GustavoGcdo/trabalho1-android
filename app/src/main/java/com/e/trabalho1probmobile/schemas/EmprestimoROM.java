package com.e.trabalho1probmobile.schemas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "emprestimos")
public class EmprestimoROM {

    @PrimaryKey(autoGenerate = true)
    public int numeroEmprestimo;


    public int equipamentoId;
    public String nomePessoa;
    public String telefone;
    public String data;
    public boolean devolvido;
}