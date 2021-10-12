package com.e.trabalho1probmobile.repositories;

import android.content.Context;

import com.e.trabalho1probmobile.database.AppDatabase;
import com.e.trabalho1probmobile.entities.Emprestimo;
import com.e.trabalho1probmobile.entities.Equipamento;
import com.e.trabalho1probmobile.schemas.EmprestimoComEquipamento;
import com.e.trabalho1probmobile.schemas.EmprestimoROM;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    private AppDatabase db;

    public EmprestimoRepository(Context context) {
        db = AppDatabase.getInstance(context);
    }

    public void inserirEmprestimo(Emprestimo emprestimo) {
        EmprestimoROM emprestimoROM = mapearParaEmprestimoROM(emprestimo);
        this.db.emprestimosDAO().inserirEmprestimos(emprestimoROM);
    }

    public ArrayList<Emprestimo> obterEmprestimos() {
        List<EmprestimoComEquipamento> emprestimos = db.emprestimosDAO().obterEmprestimos();
        ArrayList<Emprestimo> emprestimosMapeados = new ArrayList<>();

        for (EmprestimoComEquipamento emprestimoComEquipamento : emprestimos) {
            Emprestimo emprestimo = mapearParaEmprestimo(emprestimoComEquipamento);
            emprestimosMapeados.add(emprestimo);
        }

        return emprestimosMapeados;
    }

    public boolean existeEmprestimoComEquipamento(Equipamento equipamento) {
        List<EmprestimoROM> emprestimos = db.emprestimosDAO().buscarEmprestimoPorEquipamento(equipamento.getEquipamentoId());
        return !emprestimos.isEmpty();
    }

    public Emprestimo obterPorNumero(int numeroEmprestimo) {
        EmprestimoComEquipamento emprestimoComEquipamento = db.emprestimosDAO().obterPorNumero(numeroEmprestimo);
        Emprestimo emprestimoEncontrado = mapearParaEmprestimo(emprestimoComEquipamento);
        return emprestimoEncontrado;
    }


    public void atualizarEmprestimo(Emprestimo emprestimo) {
        EmprestimoROM emprestimoROM = this.mapearParaEmprestimoROM(emprestimo);
        db.emprestimosDAO().atualizarEmprestimos(emprestimoROM);
    }

    public void deletarEmprestimo(Emprestimo emprestimo) {
        EmprestimoROM emprestimoROM = new EmprestimoROM();
        emprestimoROM.numeroEmprestimo = emprestimo.getNumeroEmprestimo();
        db.emprestimosDAO().deletarEmprestimos(emprestimoROM);
    }

    private Emprestimo mapearParaEmprestimo(EmprestimoComEquipamento emprestimoComEquipamento) {
        Equipamento equipamento = new Equipamento(
                emprestimoComEquipamento.equipamento.equipamentoId,
                emprestimoComEquipamento.equipamento.nome,
                emprestimoComEquipamento.equipamento.marca
        );

        Emprestimo emprestimoMapeado = new Emprestimo(
                emprestimoComEquipamento.emprestimo.numeroEmprestimo,
                equipamento,
                emprestimoComEquipamento.emprestimo.nomePessoa,
                emprestimoComEquipamento.emprestimo.telefone,
                emprestimoComEquipamento.emprestimo.data,
                emprestimoComEquipamento.emprestimo.devolvido
        );

        return emprestimoMapeado;
    }

    private EmprestimoROM mapearParaEmprestimoROM(Emprestimo emprestimo) {
        EmprestimoROM emprestimoROM = new EmprestimoROM();
        emprestimoROM.numeroEmprestimo = emprestimo.getNumeroEmprestimo();
        emprestimoROM.equipamentoId = emprestimo.getEquipamento().getEquipamentoId();
        emprestimoROM.nomePessoa = emprestimo.getNomePessoa();
        emprestimoROM.telefone = emprestimo.getTelefone();
        emprestimoROM.data = emprestimo.getData();
        emprestimoROM.devolvido = emprestimo.getDevolvido();
        return emprestimoROM;
    }
}
