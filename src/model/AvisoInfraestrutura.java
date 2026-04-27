package model;

public class AvisoInfraestrutura extends Aviso {

    public AvisoInfraestrutura(int id, String titulo, String descricao, String urgencia, String local, Morador morador) {
        super(id, titulo, descricao, urgencia, local, morador);
    }

    @Override
    public void exibirAlerta() {
        System.out.println("🔧 [INFRAESTRUTURA] " + getTitulo());
        System.out.println("📍 Local: " + local);
    }
}