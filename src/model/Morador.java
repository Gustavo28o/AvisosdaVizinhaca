package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Morador {

    protected int id;
    protected String nome;
    protected String endereco;
    protected LocalDateTime dataCadastro;
    protected String telefone;
    protected String email;
    protected List<Aviso> avisos = new ArrayList<>();

    private static int contadorId = 1;

    public Morador(String nome, String endereco, String telefone, String email) {
        this.id = contadorId++;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.dataCadastro = LocalDateTime.now();
    }

    // =========================
    // GETTERS
    // =========================

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }

    // =========================
    // MÉTODOS
    // =========================

    public void adicionarAviso(Aviso aviso) {
        avisos.add(aviso);
    }

    public abstract boolean podeEnviarAviso();

    @Override
    public String toString() {
        return "Morador [id=" + id +
                ", nome=" + nome +
                ", endereco=" + endereco +
                ", telefone=" + telefone +
                ", email=" + email +
                ", dataCadastro=" + dataCadastro + "]";
    }
}