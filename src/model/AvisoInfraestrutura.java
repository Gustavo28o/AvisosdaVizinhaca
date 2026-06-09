package model;

public class AvisoInfraestrutura extends Aviso {

    public AvisoInfraestrutura(int id, String titulo, String descricao, String urgencia, String bairro, String rua, Morador morador) {
        super(id, titulo, descricao, urgencia, bairro, rua, morador);
    }
}
