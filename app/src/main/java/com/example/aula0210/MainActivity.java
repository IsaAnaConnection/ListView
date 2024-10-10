package com.example.aula0210;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aula0210.dao.AlunoDAO;
import com.example.aula0210.model.Aluno;

public class MainActivity extends AppCompatActivity {
    Button btnListar, btnNovo, btnAlterar, btnExcluir, btnSalvar, btnConsultar;
    EditText edtId, edtNome, edtCpf, edtTelefone, edtListar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtNome = findViewById(R.id.edtNome);
        edtId = findViewById(R.id.edtId);
        edtCpf = findViewById(R.id.edtCpf);
        edtTelefone= findViewById(R.id.edtTelefone);
        edtListar = findViewById(R.id.edtListar);

        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("id", -1);
            String nome = intent.getStringExtra("nome");
            String cpf = intent.getStringExtra("cpf");
            String telefone = intent.getStringExtra("telefone");

            // Preenche os campos se o ID for válido
            if (id != -1) {
                edtId.setText(String.valueOf(id));
                edtNome.setText(nome);
                edtCpf.setText(cpf);
                edtTelefone.setText(telefone);
            }
        }

    }
    public void novo (View view){
        edtNome.setText(null);
        edtId.setText(null);
        edtCpf.setText(null);
        edtTelefone.setText(null);
        edtListar.setText(null);

    }
    public void salvar (View view){
        Aluno a = new Aluno();
        a.setNome(edtNome.getText().toString());
        a.setCpf(edtCpf.getText().toString());
        a.setTelefone(edtTelefone.getText().toString());
        AlunoDAO dao = new AlunoDAO(this);
        long id = dao.insert(a);
        Toast.makeText(getApplicationContext(), "Aluno inserido com o ID "+id,
                Toast.LENGTH_LONG).show();
    }

    public void consultar (View view){
        AlunoDAO dao = new AlunoDAO(this);
        Aluno a = dao.read(Integer.parseInt(edtId.getText().toString()));
        edtNome.setText(a.getNome());
        edtCpf.setText(a.getCpf());
        edtTelefone.setText(a.getTelefone());
    }
    public void excluir(View view){
        Aluno a = new Aluno();
        a.setId(Integer.parseInt(edtId.getText().toString()));
        AlunoDAO dao = new AlunoDAO(this);
        dao.delete(a);
        Toast.makeText(getApplicationContext(), "Aluno Excluido!",
                Toast.LENGTH_LONG).show();
    }
    public void alterar(View view){
        Aluno a = new Aluno();
        a.setId(Integer.parseInt(edtId.getText().toString()));
        a.setNome(edtNome.getText().toString());
        a.setCpf(edtCpf.getText().toString());
        a.setTelefone(edtTelefone.getText().toString());
        AlunoDAO dao = new AlunoDAO(this);
        dao.update(a);
        Toast.makeText(getApplicationContext(), "Aluno alterado! ",
                Toast.LENGTH_LONG).show();
    }

}