package model;

import java.util.ArrayList;
import java.util.List;

public class Rua {

    private String nome;
    private List<Morador> moradores = new ArrayList<>();

    public Rua(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Morador> getMoradores() {
        return moradores;
    }

    public void adicionarMorador(Morador m) {
        moradores.add(m);
    }
}