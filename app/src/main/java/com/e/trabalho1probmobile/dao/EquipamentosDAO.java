package com.e.trabalho1probmobile.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.e.trabalho1probmobile.schemas.EquipamentoROM;

import java.util.List;

@Dao
public interface EquipamentosDAO {

    @Insert
    public void inserirEquipamentos(EquipamentoROM... equipamentos);

    @Query("SELECT * FROM equipamentos")
    public List<EquipamentoROM> obterEquipamentos();

    @Delete
    public void deleteEquipamentos(EquipamentoROM equipamentoROM);

    @Query("SELECT * FROM equipamentos WHERE equipamentoId = :equipamentoId")
    public EquipamentoROM obterPorId(int equipamentoId);

    @Update
    public void atualizarEquipamentos(EquipamentoROM... equipamentoROMS);
}
