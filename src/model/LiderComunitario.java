package model;

public class LiderComunitario extends Morador {

    public LiderComunitario(String nome, String endereco, String telefone, String email, String senha) {
        super(nome, endereco, telefone, email, senha);
    }
    @Override
    public boolean podeEnviarAviso() {
        return true;
    }
}