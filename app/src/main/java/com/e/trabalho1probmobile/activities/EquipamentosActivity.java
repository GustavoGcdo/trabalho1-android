package com.e.trabalho1probmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.e.trabalho1probmobile.R;
import com.e.trabalho1probmobile.adapters.EquipamentosAdapter;
import com.e.trabalho1probmobile.entities.Equipamento;
import com.e.trabalho1probmobile.repositories.EmprestimoRepository;
import com.e.trabalho1probmobile.repositories.EquipamentoRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EquipamentosActivity extends AppCompatActivity {

    EquipamentoRepository equipamentoRepository;
    EmprestimoRepository emprestimoRepository;
    ListView listEquipamentos;
    FloatingActionButton novoEquipamento;
    ArrayList<Equipamento> equipamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipamentos);
        equipamentoRepository = new EquipamentoRepository(getApplicationContext());
        emprestimoRepository = new EmprestimoRepository(getApplicationContext());

        configureList();
        configureButtonNovoEquipamento();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureList();
    }

    private void configureList() {
        equipamentos = equipamentoRepository.obterEquipamentos();
        listEquipamentos = findViewById(R.id.listEquipamentos);
        EquipamentosAdapter adapter = new EquipamentosAdapter(equipamentos, this);
        registerForContextMenu(listEquipamentos);
        listEquipamentos.setAdapter(adapter);
    }

    private void configureButtonNovoEquipamento() {
        novoEquipamento = findViewById(R.id.btnAddEquipamento);
        novoEquipamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EquipamentosActivity.this, FormEquipamento.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item_list, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Equipamento equipamentoSelecionado = equipamentos.get(info.position);

        switch (item.getItemId()) {
            case R.id.delete_id:
                boolean existeEmprestimoVinculado = emprestimoRepository.existeEmprestimoComEquipamento(equipamentoSelecionado);
                if (existeEmprestimoVinculado) {
                    Toast.makeText(this, "Existe um empréstimo vinculado a este equipamento!", Toast.LENGTH_SHORT).show();
                } else {
                    equipamentoRepository.deletarEquipamento(equipamentoSelecionado);
                    configureList();
                    Toast.makeText(this, equipamentoSelecionado.getNome() + " removido", Toast.LENGTH_SHORT).show();
                }

                return true;
            case R.id.alterar_id:
                Intent intent = new Intent(EquipamentosActivity.this, FormEquipamento.class);
                intent.putExtra("idEquipamento", equipamentoSelecionado.getEquipamentoId());
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}
