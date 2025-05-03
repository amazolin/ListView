package com.example.listviewbd;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lsvDados;
    private ArrayAdapter<Contato> adaptador;
    private List<Contato> listaContatos;
    private ContatoDAO dao; // Movido para escopo da classe para reutilização

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Edge-to-Edge (melhor legibilidade)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa o DAO uma única vez
        dao = new ContatoDAO(this);

        // ListView e Adaptador
        lsvDados = findViewById(R.id.lsvDados);
        atualizarLista(); // Carrega dados inicialmente

        // Clique em um item da lista
        lsvDados.setOnItemClickListener((parent, view, position, id) -> {
            Contato contatoSelecionado = listaContatos.get(position);
            abrirTelaManutencao(contatoSelecionado);
        });
    }

    // Método para abrir a tela de manutenção (reduz duplicação)
    private void abrirTelaManutencao(Contato contato) {
        Intent intent = new Intent(this, Manutencao.class);
        intent.putExtra("contato_id", contato.getId());
        intent.putExtra("contato_nome", contato.getNome());
        intent.putExtra("contato_telefone", contato.getTelefone());
        intent.putExtra("contato_email", contato.getEmail());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista(); // Atualiza a lista sempre que a Activity retorna ao foco
    }

    // Atualiza a lista de contatos
    private void atualizarLista() {
        listaContatos = dao.listarTodos();

        if (listaContatos.isEmpty()) {
            Toast.makeText(this, "Nenhum contato cadastrado!", Toast.LENGTH_SHORT).show();
        }

        adaptador = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, listaContatos);
        lsvDados.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuSair) {
            finish();
            return true;
        } else if (id == R.id.mnuSalvar) {
            startActivity(new Intent(this, Cadastro.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean sair(MenuItem item) {
        finish();
        return true;
    }
}