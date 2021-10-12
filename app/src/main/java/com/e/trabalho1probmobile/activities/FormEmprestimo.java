package com.e.trabalho1probmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.trabalho1probmobile.R;
import com.e.trabalho1probmobile.entities.Emprestimo;
import com.e.trabalho1probmobile.entities.Equipamento;
import com.e.trabalho1probmobile.repositories.EmprestimoRepository;
import com.e.trabalho1probmobile.repositories.EquipamentoRepository;

import java.util.ArrayList;

public class FormEmprestimo extends AppCompatActivity {

    EquipamentoRepository equipamentoRepository;
    EmprestimoRepository emprestimoRepository;
    EditText editNome;
    EditText editTelefone;
    EditText editData;
    CheckBox cbEstaDevolvido;
    Spinner spinnerEquipamentos;
    ArrayList<Equipamento> equipamentos;
    Emprestimo emprestimoAEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_emprestimo);
        equipamentoRepository = new EquipamentoRepository(getApplicationContext());
        emprestimoRepository = new EmprestimoRepository(getApplicationContext());

        recuperarIds();
        configurarSpinner();
        configurarEdicaoDeEmprestimoSeNecessario();
        configuraBotaoSalvar();
    }

    private void recuperarIds() {
        editNome = findViewById(R.id.editNome);
        editTelefone = findViewById(R.id.editTelefone);
        editData = findViewById(R.id.editData);
        cbEstaDevolvido = findViewById(R.id.checkDevolvido);
    }

    private void configurarSpinner() {
        spinnerEquipamentos = findViewById(R.id.spinnerEquipamentos);
        equipamentos = equipamentoRepository.obterEquipamentos();
        ArrayAdapter<Equipamento> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, equipamentos);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEquipamentos.setAdapter(arrayAdapter);
    }

    private void configuraBotaoSalvar() {
        Button btnSalvarEmprestimo = findViewById(R.id.btnSalvarEmprestimo);
        btnSalvarEmprestimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarEmprestimo();
            }
        });
    }

    private void salvarEmprestimo() {
        Equipamento equipamentoSelecionado = (Equipamento) spinnerEquipamentos.getSelectedItem();
        String nomePessoa = editNome.getText().toString();
        String telefone = editTelefone.getText().toString();
        String data = editData.getText().toString();
        boolean estaDevolvido = cbEstaDevolvido.isChecked();

        if (equipamentoSelecionado == null) {
            Toast.makeText(this, "Não é possivel cadastrar sem equipamento", Toast.LENGTH_SHORT).show();
            return;
        }

        if (emprestimoAEditar == null) {
            Emprestimo novoEmprestimo = new Emprestimo(equipamentoSelecionado, nomePessoa, telefone, data, estaDevolvido);
            emprestimoRepository.inserirEmprestimo(novoEmprestimo);
        } else {
            emprestimoAEditar.setEquipamento(equipamentoSelecionado);
            emprestimoAEditar.setNomePessoa(nomePessoa);
            emprestimoAEditar.setTelefone(telefone);
            emprestimoAEditar.setData(data);
            emprestimoAEditar.setDevolvido(estaDevolvido);
            emprestimoRepository.atualizarEmprestimo(emprestimoAEditar);
        }

        FormEmprestimo.this.finish();
    }

    private void configurarEdicaoDeEmprestimoSeNecessario() {
        Intent intent = getIntent();
        int idEmprestimo = intent.getIntExtra("idEmprestimo", 0);
        if (idEmprestimo != 0) {
            emprestimoAEditar = emprestimoRepository.obterPorNumero(idEmprestimo);
            editNome.setText(emprestimoAEditar.getNomePessoa());
            editTelefone.setText(emprestimoAEditar.getTelefone());
            editData.setText(emprestimoAEditar.getData());
            cbEstaDevolvido.setChecked(emprestimoAEditar.getDevolvido());
            int indiceEquipamento = obterIndiceEquipamento(emprestimoAEditar.getEquipamento());
            spinnerEquipamentos.setSelection(indiceEquipamento);
        }
    }

    public int obterIndiceEquipamento(Equipamento equipamentoProcurado) {
        for (int i = 0; i < equipamentos.size(); i++) {
            Equipamento equipamento = equipamentos.get(i);
            if (equipamentoProcurado.getEquipamentoId() == equipamento.getEquipamentoId()) {
                return i;
            }
        }
        return -1;
    }
}
