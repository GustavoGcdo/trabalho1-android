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
import androidx.appcompat.widget.Toolbar;

import com.e.trabalho1probmobile.R;
import com.e.trabalho1probmobile.adapters.EmprestimosAdapter;
import com.e.trabalho1probmobile.entities.Emprestimo;
import com.e.trabalho1probmobile.repositories.EmprestimoRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EmprestimosActivity extends AppCompatActivity {

    EmprestimoRepository emprestimoRepository;
    ArrayList<Emprestimo> emprestimos;
    ListView listEmprestimos;
    FloatingActionButton novoEmprestimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestimos);
        emprestimoRepository = new EmprestimoRepository(getApplicationContext());

//        // toolbar
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        // add back arrow to toolbar
//        if (getSupportActionBar() != null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }

        configureList();
        configureButtonNovoEmprestimo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureList();
    }

    private void configureList() {
        emprestimos = emprestimoRepository.obterEmprestimos();
        listEmprestimos = findViewById(R.id.listEmprestimos);
        EmprestimosAdapter adapter = new EmprestimosAdapter(emprestimos, EmprestimosActivity.this);
        this.registerForContextMenu(listEmprestimos);
        listEmprestimos.setAdapter(adapter);
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
        Emprestimo emprestimoSelecionado = emprestimos.get(info.position);

        switch (item.getItemId()) {
            case R.id.delete_id:
                emprestimoRepository.deletarEmprestimo(emprestimoSelecionado);
                configureList();
                Toast.makeText(this, "Emprestimo N°" + emprestimoSelecionado.getNumeroEmprestimo() + " removido", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.alterar_id:
                Intent intent = new Intent(EmprestimosActivity.this, FormEmprestimo.class);
                intent.putExtra("idEmprestimo", emprestimoSelecionado.getNumeroEmprestimo());
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void configureButtonNovoEmprestimo() {
        novoEmprestimo = findViewById(R.id.btnAddEmprestimo);
        novoEmprestimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmprestimosActivity.this, FormEmprestimo.class);
                startActivity(intent);
            }
        });
    }
}
