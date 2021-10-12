package com.e.trabalho1probmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.e.trabalho1probmobile.R;
import com.e.trabalho1probmobile.entities.Equipamento;
import com.e.trabalho1probmobile.repositories.EquipamentoRepository;

public class FormEquipamento extends AppCompatActivity {

    EquipamentoRepository equipamentoRepository;
    Equipamento equipamentoAEditar;
    EditText editNomeEquipamento;
    EditText editMarcaEquipamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_equipamento);
        equipamentoRepository = new EquipamentoRepository(getApplicationContext());

        recuperarIds();
        configurarBotaoSalvar();
        configurarEdicaoDeEquipamentoSeNecessario();
    }

    private void configurarBotaoSalvar() {
        Button btnSalvarEquipamento = findViewById(R.id.btnSalvarEquipamento);
        btnSalvarEquipamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNomeEquipamento.getText().toString();
                String marca = editMarcaEquipamento.getText().toString();

                if(equipamentoAEditar == null) {
                    Equipamento novoEquipamento = new Equipamento(nome, marca);
                    equipamentoRepository.inserirEquipamento(novoEquipamento);
                } else {
                    equipamentoAEditar.setNome(nome);
                    equipamentoAEditar.setMarca(marca);
                    equipamentoRepository.atualizarEquipamento(equipamentoAEditar);
                }

                FormEquipamento.this.finish();
            }
        });
    }

    private void recuperarIds() {
        editNomeEquipamento = findViewById(R.id.editEquipamento);
        editMarcaEquipamento = findViewById(R.id.editMarca);

    }

    private void configurarEdicaoDeEquipamentoSeNecessario() {
        Intent intent = getIntent();
        int idEquipamento = intent.getIntExtra("idEquipamento", 0);
        if (idEquipamento != 0) {
            equipamentoAEditar = equipamentoRepository.obterPorId(idEquipamento);
            editNomeEquipamento.setText(equipamentoAEditar.getNome());
            editMarcaEquipamento.setText(equipamentoAEditar.getMarca());
        }
    }
}
