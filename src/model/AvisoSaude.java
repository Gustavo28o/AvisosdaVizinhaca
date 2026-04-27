package model;

public class AvisoSaude extends Aviso {

    public AvisoSaude(int id, String titulo, String descricao, String urgencia, String local, Morador morador) {
        super(id, titulo, descricao, urgencia, local, morador);
    }

    @Override
    public void exibirAlerta() {
        System.out.println("🏥 [SAÚDE] " + getTitulo());
        System.out.println("📍 Local: " + local);
    }
}