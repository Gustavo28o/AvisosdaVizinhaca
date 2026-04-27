package model;

import java.util.ArrayList;
import java.util.List;

public class Bairro {

    private String nome;
    private String regiao;
    private List<Rua> ruas = new ArrayList<>();

    public Bairro(String nome, String regiao) {
        this.nome = nome;
        this.regiao = regiao;
    }

    public String getNome() {
        return nome;
    }

    public String getRegiao() {
        return regiao;
    }

    public List<Rua> getRuas() {
        return ruas;
    }

    public void adicionarRua(Rua rua) {
        ruas.add(rua);
    }
}