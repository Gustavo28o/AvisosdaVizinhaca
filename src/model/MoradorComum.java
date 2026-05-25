package model;

public class MoradorComum extends Morador {

    public MoradorComum(String nome, String endereco, String telefone, String email, String senha) {
        super(nome, endereco, telefone, email, senha);
    }

    @Override
    public boolean podeEnviarAviso() {
        return true;
    }
}