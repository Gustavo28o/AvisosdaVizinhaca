package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Morador {

    protected int id;
    protected String nome;
    protected String endereco;
    protected String bairro;
    protected String rua;
    protected LocalDateTime dataCadastro;
    protected String senha;
    protected String telefone;
    protected String email;
    protected List<Aviso> avisos = new ArrayList<>();

    private static int contadorId = 1;

    public Morador(String nome, String bairro, String rua, String telefone, String email, String senha) {
        this.id = contadorId++;
        this.nome = nome;
        this.bairro = bairro;
        this.rua = rua;
        this.endereco = rua + " - " + bairro;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getBairro() { return bairro; }
    public String getRua() { return rua; }
    public String getSenha() { return senha; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public List<Aviso> getAvisos() { return avisos; }

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
