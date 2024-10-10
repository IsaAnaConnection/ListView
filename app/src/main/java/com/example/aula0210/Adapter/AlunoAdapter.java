package com.example.aula0210.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aula0210.R;
import com.example.aula0210.model.Aluno;

import java.util.List;

public class AlunoAdapter extends ArrayAdapter<Aluno> {
    public AlunoAdapter(Context context, List<Aluno> alunos) {
        super(context, 0, alunos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_aluno, parent, false);
        }

        TextView tvNome = convertView.findViewById(R.id.tvNome);
        TextView tvCpf = convertView.findViewById(R.id.tvCpf);
        TextView tvTelefone = convertView.findViewById(R.id.tvTelefone);

        tvNome.setText(aluno.getNome());
        tvCpf.setText(aluno.getCpf());
        tvTelefone.setText(aluno.getTelefone());

        return convertView;
    }
}
