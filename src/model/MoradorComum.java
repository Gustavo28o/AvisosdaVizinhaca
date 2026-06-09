package model;

public class MoradorComum extends Morador {

    public MoradorComum(String nome, String bairro, String rua, String telefone, String email, String senha) {
        super(nome, bairro, rua, telefone, email, senha);
    }

    @Override
    public boolean podeEnviarAviso() {
        return true;
    }
}
