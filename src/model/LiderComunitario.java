package model;

public class LiderComunitario extends Morador {

    public LiderComunitario(String nome, String bairro, String rua, String telefone, String email, String senha) {
        super(nome, bairro, rua, telefone, email, senha);
    }

    @Override
    public boolean podeEnviarAviso() {
        return true;
    }
}
