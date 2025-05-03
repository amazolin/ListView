package com.example.listviewbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private SQLiteDatabase banco;
    private static final String TABELA = "contatos"; // Nome corrigido da tabela (de "aluno" para "contatos")

    public ContatoDAO(Context context) {
        ConnectionFactory helper = new ConnectionFactory(context);
        banco = helper.getWritableDatabase();
    }

    public long inserir(Contato contato) {
        ContentValues valores = new ContentValues();
        valores.put("nome", contato.getNome());
        valores.put("email", contato.getEmail());
        valores.put("telefone", contato.getTelefone());
        return banco.insert(TABELA, null, valores);
    }

    public List<Contato> listarTodos() {
        List<Contato> lista = new ArrayList<>();
        Cursor cursor = banco.query(TABELA,
                new String[]{"id", "nome", "email", "telefone"},
                null, null, null, null, "nome COLLATE NOCASE ASC"); // Ordenação case-insensitive

        while (cursor.moveToNext()) {
            Contato c = new Contato(
                    cursor.getLong(0),   // id
                    cursor.getString(1), // nome
                    cursor.getString(2), // email
                    cursor.getString(3)  // telefone
            );
            lista.add(c);
        }

        cursor.close();
        return lista;
    }

    public int atualizar(Contato contato) {
        ContentValues valores = new ContentValues();
        valores.put("nome", contato.getNome());
        valores.put("email", contato.getEmail());
        valores.put("telefone", contato.getTelefone());

        return banco.update(TABELA, valores,
                "id = ?",
                new String[]{String.valueOf(contato.getId())});
    }

    public int remover(long id) {
        return banco.delete(TABELA,
                "id = ?",
                new String[]{String.valueOf(id)});
    }
}