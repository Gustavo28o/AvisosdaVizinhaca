package model;

public class AvisoSeguranca extends Aviso {

    public AvisoSeguranca(int id, String titulo, String descricao, String urgencia, String bairro, String rua, Morador morador) {
        super(id, titulo, descricao, urgencia, bairro, rua, morador);
    }
}
