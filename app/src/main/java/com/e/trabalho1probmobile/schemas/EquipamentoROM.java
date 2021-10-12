package com.e.trabalho1probmobile.schemas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "equipamentos")
public class EquipamentoROM {
    @PrimaryKey(autoGenerate = true)
    public int equipamentoId;
    public String nome;
    public String marca;
}
