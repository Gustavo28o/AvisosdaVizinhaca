package model;

public class MoradorComum extends Morador {

    public MoradorComum(String nome, String endereco, String telefone, String email) {
        super(nome, endereco, telefone, email);
    }

    @Override
    public boolean podeEnviarAviso() {
        return true;
    }
}