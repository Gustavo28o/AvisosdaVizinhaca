package model;

public class LiderComunitario extends Morador {

    public LiderComunitario(String nome, String endereco, String telefone, String email) {
        super(nome, endereco, telefone, email);
    }

    @Override
    public boolean podeEnviarAviso() {
        return true;
    }
}