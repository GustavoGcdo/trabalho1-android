package com.e.trabalho1probmobile.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.e.trabalho1probmobile.R;
import com.e.trabalho1probmobile.entities.Emprestimo;

import java.util.ArrayList;

public class EmprestimosAdapter extends BaseAdapter {

    private ArrayList<Emprestimo> emprestimos;
    private Context mContext;

    public EmprestimosAdapter(ArrayList<Emprestimo> emprestimos, Context mContext) {
        this.emprestimos = emprestimos;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return emprestimos.size();
    }

    @Override
    public Object getItem(int position) {
        return emprestimos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.adapter_emprestimo_item, null);

        Emprestimo emprestimo = (Emprestimo) this.getItem(position);

        TextView txtEquipmentName = view.findViewById(R.id.txtEquipmentName);
        TextView txtPersonName = view.findViewById(R.id.txtPersonName);
        TextView txtNumber = view.findViewById(R.id.txtNumber);
        TextView txtLoanDate = view.findViewById(R.id.txtLoanDate);

        txtEquipmentName.setText(emprestimo.getEquipamento().getNome());
        txtPersonName.setText(emprestimo.getNomePessoa());
        txtNumber.setText(emprestimo.getNumeroEmprestimo().toString());
        txtLoanDate.setText(emprestimo.getData());

        return view;
    }
}
