package model;

public class AvisoSaude extends Aviso {

    public AvisoSaude(int id, String titulo, String descricao, String urgencia, String bairro, String rua, Morador morador) {
        super(id, titulo, descricao, urgencia, bairro, rua, morador);
    }
}
