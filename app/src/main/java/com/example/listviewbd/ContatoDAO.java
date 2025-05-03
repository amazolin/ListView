// Classe de dados simulando banco em memória
package com.example.listviewbd;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private static List<Contato> lista = new ArrayList<>();

    public static List<Contato> getLista() {
        return lista;
    }

    public static void adicionar(Contato contato) {
        lista.add(contato);
    }

    public static void atualizar(int index, Contato contato) {
        lista.set(index, contato);
    }

    public static void remover(int index) {
        lista.remove(index);
    }

    public static Contato get(int index) {
        return lista.get(index);
    }
}