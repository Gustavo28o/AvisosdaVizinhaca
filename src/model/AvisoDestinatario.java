package model;

import java.time.LocalDateTime;

/**
 * Representa um aviso enviado diretamente para um morador específico.
 * Armazenado em memória via ArrayList no MoradorService.
 */
public class AvisoDestinatario {

    private int id;
    private Aviso aviso;
    private Morador destinatario;
    private boolean lido;
    private LocalDateTime dataEnvio;

    public AvisoDestinatario(int id, Aviso aviso, Morador destinatario) {
        this.id = id;
        this.aviso = aviso;
        this.destinatario = destinatario;
        this.lido = false;
        this.dataEnvio = LocalDateTime.now();
    }

    public int getId() { return id; }
    public Aviso getAviso() { return aviso; }
    public Morador getDestinatario() { return destinatario; }
    public boolean isLido() { return lido; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }

    public void marcarComoLido() {
        this.lido = true;
    }

    @Override
    public String toString() {
        String status = lido ? "✅ Lido" : "🔔 Não lido";
        return "[" + status + "] De: " + aviso.getMorador().getNome()
                + " | " + aviso.getTitulo()
                + " | Urgência: " + aviso.getUrgencia()
                + " | " + dataEnvio.toLocalDate();
    }
}
