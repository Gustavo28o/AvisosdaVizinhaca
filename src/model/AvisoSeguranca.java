package model;

public class AvisoSeguranca extends Aviso{


    public AvisoSeguranca(int id, String titulo, String descricao, String urgencia, String local, Morador morador) {
        super(id, titulo, descricao, urgencia, local, morador);
    }

    @Override
    public void exibirAlerta() {
        System.out.println("🚨🚨 [SEGURANÇA] " + getTitulo().toUpperCase() + " 🚨🚨");
        System.out.println("📍 Local: " + local);
    }
}

