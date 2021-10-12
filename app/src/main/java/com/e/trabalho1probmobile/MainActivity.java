package com.e.trabalho1probmobile;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.e.trabalho1probmobile.activities.EmprestimosActivity;
import com.e.trabalho1probmobile.activities.EquipamentosActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout btnEmprestimos = findViewById(R.id.btnEmprestimos);
        LinearLayout btnEquipamentos = findViewById(R.id.btnEquipamentos);

        btnEmprestimos.setOnClickListener(registerEventToActivity(EmprestimosActivity.class));
        btnEquipamentos.setOnClickListener(registerEventToActivity(EquipamentosActivity.class));
    }


    private View.OnClickListener registerEventToActivity(Class classeDestino) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classeDestino);
                startActivity(intent);
            }
        };
    }
}
