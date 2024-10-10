package com.example.aula0210;

import android.content.Intent;  // Importar a classe Intent
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aula0210.dao.AlunoDAO;
import com.example.aula0210.model.Aluno;

public class MainActivity extends AppCompatActivity {
    Button btnNovo, btnAlterar, btnExcluir, btnSalvar, btnConsultar;
    EditText edtId, edtNome, edtCpf, edtTelefone;
    TextView txtSair; // Declare txtSair

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

        // Inicialize os componentes
        edtNome = findViewById(R.id.edtNome);
        edtId = findViewById(R.id.edtId);
        edtCpf = findViewById(R.id.edtCpf);
        edtTelefone = findViewById(R.id.edtTelefone);
        txtSair = findViewById(R.id.txtSair); // Inicialize o txtSair

        // Defina o listener para o txtSair
        txtSair.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TelaLista.class);
            startActivity(intent);
        });
    }

    public void novo(View view) {
        edtNome.setText(null);
        edtId.setText(null);
        edtCpf.setText(null);
        edtTelefone.setText(null);
    }

    public void salvar(View view) {
        Aluno a = new Aluno();
        a.setNome(edtNome.getText().toString());
        a.setCpf(edtCpf.getText().toString());
        a.setTelefone(edtTelefone.getText().toString());
        AlunoDAO dao = new AlunoDAO(this);
        long id = dao.insert(a);
        Toast.makeText(getApplicationContext(), "Aluno inserido com o ID " + id, Toast.LENGTH_LONG).show();
    }

    public void consultar(View view) {
        AlunoDAO dao = new AlunoDAO(this);
        Aluno a = dao.read(Integer.parseInt(edtId.getText().toString()));
        edtNome.setText(a.getNome());
        edtCpf.setText(a.getCpf());
        edtTelefone.setText(a.getTelefone());
    }

    public void excluir(View view) {
        Aluno a = new Aluno();
        a.setId(Integer.parseInt(edtId.getText().toString()));
        AlunoDAO dao = new AlunoDAO(this);
        dao.delete(a);
        Toast.makeText(getApplicationContext(), "Aluno Excluído!", Toast.LENGTH_LONG).show();
    }

    public void alterar(View view) {
        Aluno a = new Aluno();
        a.setId(Integer.parseInt(edtId.getText().toString()));
        a.setNome(edtNome.getText().toString());
        a.setCpf(edtCpf.getText().toString());
        a.setTelefone(edtTelefone.getText().toString());
        AlunoDAO dao = new AlunoDAO(this);
        dao.update(a);
        Toast.makeText(getApplicationContext(), "Aluno alterado!", Toast.LENGTH_LONG).show();
    }
}