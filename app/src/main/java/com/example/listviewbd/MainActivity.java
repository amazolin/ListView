package com.example.listviewbd;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lsvDados = findViewById(R.id.lsvDados);

        lsvDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contatoSelecionado = listaContatos.get(position);
                Intent intent = new Intent(MainActivity.this, Manutencao.class);
                intent.putExtra("contato_index", position);
                intent.putExtra("contato_nome", contatoSelecionado.getNome());
                intent.putExtra("contato_telefone", contatoSelecionado.getTelefone());
                intent.putExtra("contato_email", contatoSelecionado.getEmail());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Atualiza a lista sempre que a tela volta a ser exibida
        atualizarLista();
    }

    private void atualizarLista() {
        listaContatos = ContatoDAO.getLista();
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
        } else if (id == R.id.menu_cadastro) {
            Intent intent = new Intent(this, Cadastro.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean sair(MenuItem item) {
        // Lógica para "sair"
        finish();
        return true;
    }

}