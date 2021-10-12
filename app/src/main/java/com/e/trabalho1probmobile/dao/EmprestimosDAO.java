package com.e.trabalho1probmobile.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.e.trabalho1probmobile.schemas.EmprestimoComEquipamento;
import com.e.trabalho1probmobile.schemas.EmprestimoROM;

import java.util.List;

@Dao
public interface EmprestimosDAO {

    @Insert
    public void inserirEmprestimos(EmprestimoROM... emprestimos);

    @Query("SELECT * FROM emprestimos")
    public List<EmprestimoComEquipamento> obterEmprestimos();

    @Delete
    public void deletarEmprestimos(EmprestimoROM emprestimoROM);

    @Query("SELECT * FROM emprestimos WHERE equipamentoId = :idEquipamento")
    public List<EmprestimoROM> buscarEmprestimoPorEquipamento(int idEquipamento);

    @Query("SELECT * FROM emprestimos WHERE numeroEmprestimo = :numeroEmprestimo")
    public EmprestimoComEquipamento obterPorNumero(int numeroEmprestimo);

    @Update
    public void atualizarEmprestimos(EmprestimoROM... emprestimoROMS);

}
