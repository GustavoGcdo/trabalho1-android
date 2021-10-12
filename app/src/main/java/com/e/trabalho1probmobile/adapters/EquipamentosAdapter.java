package com.e.trabalho1probmobile.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.e.trabalho1probmobile.R;
import com.e.trabalho1probmobile.entities.Emprestimo;
import com.e.trabalho1probmobile.entities.Equipamento;

import java.util.ArrayList;

public class EquipamentosAdapter extends BaseAdapter {

    private ArrayList<Equipamento> equipamentos;
    private Context mContext;

    public EquipamentosAdapter(ArrayList<Equipamento> equipamentos, Context mContext) {
        this.equipamentos = equipamentos;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return equipamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return equipamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.adapter_equipamento_item, null);

        Equipamento equipamento = (Equipamento) this.getItem(position);

        TextView txtNomeEquipamento = view.findViewById(R.id.txtNomeEquipamento);
        TextView txtMarca = view.findViewById(R.id.txtMarcaEquipamento);

        txtNomeEquipamento.setText(equipamento.getNome());
        txtMarca.setText(equipamento.getMarca());

        return view;
    }


}