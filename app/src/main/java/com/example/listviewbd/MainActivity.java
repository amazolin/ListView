package com.example.listviewbd;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lsvDados;

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

        //Fonte de dados
        List<Contato> lista = new ArrayList<Contato>();
        lista.add(new Contato("Jose", "(11)3659996325", "jose@teste.com"));
        lista.add(new Contato("Anora", "(12)8900326555", "anora@teste.com"));
        lista.add(new Contato("Kobori", "(21)9320058522", "kobori@teste.com"));
        lista.add(new Contato("Zama", "(61)88752563625", "danilo@teste.com"));


        //Adaptador do ListView
        ArrayAdapter<Contato> adaptador = new ArrayAdapter<Contato>(
                this, android.R.layout.simple_list_item_1, lista);
        //Relacionando o adaptador ao componente ListView
        lsvDados.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return true;
    }

    public void sair (MenuItem item) {
        finish();
    }

}