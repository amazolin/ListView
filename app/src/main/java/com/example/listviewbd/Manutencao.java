package com.example.listviewbd;

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

public class Manutencao extends AppCompatActivity {

    private EditText edtNomeManutencao;
    private EditText edtEmailManutencao;
    private EditText edtTelefoneManutencao;
    private Button btnExcluirManutencao;
    private Button btnAtualizarManutencao;
    private Button btnVoltarManutencao;
    private long contatoId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manutencao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa componentes
        edtNomeManutencao = findViewById(R.id.edtNomeManutencao);
        edtEmailManutencao = findViewById(R.id.edtEmailManutencao);
        edtTelefoneManutencao = findViewById(R.id.edtTelefoneManutencao);
        btnExcluirManutencao = findViewById(R.id.btnExcluirManutencao);
        btnAtualizarManutencao = findViewById(R.id.btnAtualizarManutencao);
        btnVoltarManutencao = findViewById(R.id.btnVoltarManutencao);

        // Recupera dados do Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("contato_id")) {
            contatoId = intent.getLongExtra("contato_id", -1);
            edtNomeManutencao.setText(intent.getStringExtra("contato_nome"));
            edtEmailManutencao.setText(intent.getStringExtra("contato_email"));
            edtTelefoneManutencao.setText(intent.getStringExtra("contato_telefone"));
        }

        // Listeners dos botões
        btnAtualizarManutencao.setOnClickListener(v -> atualizarContato());
        btnExcluirManutencao.setOnClickListener(v -> excluirContato());
        btnVoltarManutencao.setOnClickListener(v -> finish());
    }

    private void atualizarContato() {
        if (contatoId == -1) {
            Toast.makeText(this, "Contato inválido!", Toast.LENGTH_SHORT).show();
            return;
        }

        String nome = edtNomeManutencao.getText().toString().trim();
        String email = edtEmailManutencao.getText().toString().trim();
        String telefone = edtTelefoneManutencao.getText().toString().trim();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Contato contatoAtualizado = new Contato(contatoId, nome, telefone, email);
        ContatoDAO dao = new ContatoDAO(this);
        boolean sucesso = dao.atualizar(contatoAtualizado) > 0;

        if (sucesso) {
            Toast.makeText(this, "Contato atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro ao atualizar o contato!", Toast.LENGTH_SHORT).show();
        }
    }

    private void excluirContato() {
        if (contatoId == -1) {
            Toast.makeText(this, "Contato inválido!", Toast.LENGTH_SHORT).show();
            return;
        }

        ContatoDAO dao = new ContatoDAO(this);
        boolean sucesso = dao.remover(contatoId) > 0;

        if (sucesso) {
            Toast.makeText(this, "Contato excluído com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro ao excluir o contato!", Toast.LENGTH_SHORT).show();
        }
    }
}