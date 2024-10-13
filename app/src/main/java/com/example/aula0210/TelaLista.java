package com.example.aula0210;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aula0210.Adapter.AlunoAdapter;
import com.example.aula0210.dao.AlunoDAO;
import com.example.aula0210.model.Aluno;

import java.util.List;

public class TelaLista extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista);

        // Configura a Toolbar como a ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Ajusta o padding das barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        carregarAlunos(); // Carrega os alunos no ListView

        // Listener para clicar em um item da lista
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
            Intent intent = new Intent(TelaLista.this, MainActivity.class);
            // Passa os dados do aluno selecionado para a MainActivity
            intent.putExtra("id", alunoSelecionado.getId());
            intent.putExtra("nome", alunoSelecionado.getNome());
            intent.putExtra("cpf", alunoSelecionado.getCpf());
            intent.putExtra("telefone", alunoSelecionado.getTelefone());
            startActivity(intent);
        });
    }

    // Infla o menu na ActionBar/Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    // Lida com os cliques nos itens do menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.novo) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;  // Ação tratada
        } else if (id == R.id.sair) {
            finishAffinity();
            return true;  // Ação tratada
        }

        return super.onOptionsItemSelected(item);  // Deixe o sistema tratar o restante
    }

    private void carregarAlunos() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.obterTodos();
        AlunoAdapter adapter = new AlunoAdapter(this, alunos);
        listView.setAdapter(adapter);
    }
}
