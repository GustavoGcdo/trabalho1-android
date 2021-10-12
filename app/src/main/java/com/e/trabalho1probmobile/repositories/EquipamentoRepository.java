package com.e.trabalho1probmobile.repositories;

import android.content.Context;

import com.e.trabalho1probmobile.database.AppDatabase;
import com.e.trabalho1probmobile.entities.Equipamento;
import com.e.trabalho1probmobile.schemas.EquipamentoROM;

import java.util.ArrayList;
import java.util.List;

public class EquipamentoRepository {
    private AppDatabase db;

    public EquipamentoRepository(Context context) {
        db = AppDatabase.getInstance(context);
    }

    public void inserirEquipamento(Equipamento equipamento) {
        EquipamentoROM novoEquipamentoROM = mapearParaEquipamentoROM(equipamento);
        this.db.equipamentosDAO().inserirEquipamentos(novoEquipamentoROM);
    }

    public ArrayList<Equipamento> obterEquipamentos() {
        List<EquipamentoROM> equipamentos = db.equipamentosDAO().obterEquipamentos();
        ArrayList<Equipamento> equipamentosMapeados = new ArrayList<>();

        for (EquipamentoROM equipamentoROM : equipamentos) {
            equipamentosMapeados.add(mapearParaEquipamento(equipamentoROM));
        }

        return equipamentosMapeados;
    }

    public void deletarEquipamento(Equipamento equipamento) {
        EquipamentoROM equipamentoADeletar = new EquipamentoROM();
        equipamentoADeletar.equipamentoId = equipamento.getEquipamentoId();
        equipamentoADeletar.nome = equipamento.getNome();
        equipamentoADeletar.marca = equipamento.getMarca();
        db.equipamentosDAO().deleteEquipamentos(equipamentoADeletar);
    }

    public Equipamento obterPorId(int idEquipamento) {
        EquipamentoROM equipamentoROM = db.equipamentosDAO().obterPorId(idEquipamento);
        return mapearParaEquipamento(equipamentoROM);
    }

    public void atualizarEquipamento(Equipamento equipamentoAEditar) {
        EquipamentoROM equipamentoROM = mapearParaEquipamentoROM(equipamentoAEditar);
        db.equipamentosDAO().atualizarEquipamentos(equipamentoROM);
    }

    private Equipamento mapearParaEquipamento(EquipamentoROM equipamentoROM) {
        Equipamento equipamento = new Equipamento(equipamentoROM.equipamentoId, equipamentoROM.nome, equipamentoROM.marca);
        return equipamento;
    }

    private EquipamentoROM mapearParaEquipamentoROM(Equipamento equipamento) {
        EquipamentoROM equipamentoROM = new EquipamentoROM();
        equipamentoROM.equipamentoId = equipamento.getEquipamentoId();
        equipamentoROM.nome = equipamento.getNome();
        equipamentoROM.marca = equipamento.getMarca();
        return equipamentoROM;
    }
}
