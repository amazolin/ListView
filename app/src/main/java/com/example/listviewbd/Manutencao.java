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
    private int contatoIndex = -1;

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

        edtNomeManutencao = findViewById(R.id.edtNomeManutencao);
        edtEmailManutencao = findViewById(R.id.edtEmailManutencao);
        edtTelefoneManutencao = findViewById(R.id.edtTelefoneManutencao);
        btnExcluirManutencao = findViewById(R.id.btnExcluirManutencao);
        btnAtualizarManutencao = findViewById(R.id.btnAtualizarManutencao);
        btnVoltarManutencao = findViewById(R.id.btnVoltarManutencao);

        // Recupera os dados do contato selecionado
        Intent intent = getIntent();
        if (intent.hasExtra("contato_index")) {
            contatoIndex = intent.getIntExtra("contato_index", -1);
            edtNomeManutencao.setText(intent.getStringExtra("contato_nome"));
            edtTelefoneManutencao.setText(intent.getStringExtra("contato_telefone"));
            edtEmailManutencao.setText(intent.getStringExtra("contato_email"));
        }

        btnAtualizarManutencao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contatoIndex != -1) {
                    String nome = edtNomeManutencao.getText().toString();
                    String email = edtEmailManutencao.getText().toString();
                    String telefone = edtTelefoneManutencao.getText().toString();

                    if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty()) {
                        Contato contatoAtualizado = new Contato(nome, telefone, email);
                        ContatoDAO.atualizar(contatoIndex, contatoAtualizado);
                        Toast.makeText(Manutencao.this, "Aluno atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish(); // Retorna para a tela principal
                    } else {
                        Toast.makeText(Manutencao.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Manutencao.this, "Erro ao identificar o aluno!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExcluirManutencao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contatoIndex != -1) {
                    ContatoDAO.remover(contatoIndex);
                    Toast.makeText(Manutencao.this, "Aluno excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    finish(); // Retorna para a tela principal
                } else {
                    Toast.makeText(Manutencao.this, "Erro ao identificar o aluno!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVoltarManutencao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Simplesmente volta para a tela anterior
            }
        });
    }
}