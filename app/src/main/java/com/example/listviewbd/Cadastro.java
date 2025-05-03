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

public class Cadastro extends AppCompatActivity {

    private EditText edtNomeCadastro;
    private EditText edtEmailCadastro;
    private EditText edtTelefoneCadastro;
    private Button btnSalvarCadastro;
    private Button btnVoltarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNomeCadastro = findViewById(R.id.edtNomeCadastro);
        edtEmailCadastro = findViewById(R.id.edtEmailCadastro);
        edtTelefoneCadastro = findViewById(R.id.edtTelefoneCadastro);
        btnSalvarCadastro = findViewById(R.id.btnSalvarCadastro);
        btnVoltarCadastro = findViewById(R.id.btnVoltarCadastro);

        btnSalvarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNomeCadastro.getText().toString();
                String email = edtEmailCadastro.getText().toString();
                String telefone = edtTelefoneCadastro.getText().toString();

                if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty()) {
                    Contato novoContato = new Contato(nome, telefone, email);
                    ContatoDAO.adicionar(novoContato);
                    Toast.makeText(Cadastro.this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish(); // Retorna para a tela principal após salvar
                } else {
                    Toast.makeText(Cadastro.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVoltarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Simplesmente volta para a tela anterior
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Este método é chamado quando a Activity se torna visível para o usuário.
        // Geralmente, você pode realizar aqui tarefas como iniciar animações,
        // reproduzir vídeos ou áudios, ou atualizar a interface do usuário
        // com dados que podem ter mudado enquanto a Activity estava em segundo plano.
        // Por exemplo, você poderia limpar os campos do formulário aqui,
        // caso queira que eles estejam vazios ao retornar para esta tela.
        edtNomeCadastro.setText("");
        edtEmailCadastro.setText("");
        edtTelefoneCadastro.setText("");
        System.out.println("Cadastro: onStart() chamado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Este método é chamado quando a Activity está prestes a interagir com o usuário.
        // É um bom lugar para iniciar ou continuar processos que precisam estar ativos
        // enquanto o usuário interage com a tela. Isso pode incluir iniciar listeners,
        // iniciar atualizações de dados em tempo real ou adquirir recursos exclusivos.
        System.out.println("Cadastro: onResume() chamado");
    }
}