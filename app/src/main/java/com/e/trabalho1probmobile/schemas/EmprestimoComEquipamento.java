package com.e.trabalho1probmobile.schemas;

import androidx.room.Embedded;
import androidx.room.Relation;

public class EmprestimoComEquipamento {
    @Embedded
    public EmprestimoROM emprestimo;

    @Relation(
            parentColumn = "equipamentoId",
            entityColumn = "equipamentoId"
    )
    public EquipamentoROM equipamento;
}
