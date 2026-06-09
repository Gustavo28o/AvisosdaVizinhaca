package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Aviso {
    protected int id;
    protected String titulo;
    protected String descricao;
    protected String urgencia;
    protected LocalDateTime dataHora;
    protected String bairro;
    protected String rua;
    protected String local;
    protected Morador morador;
    protected List<ConfirmacaoVisualizacao> confirmacoes = new ArrayList<>();

    public Aviso(int id, String titulo, String descricao, String urgencia,
                 String bairro, String rua, Morador morador) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.urgencia = urgencia;
        this.bairro = bairro;
        this.rua = rua;
        this.local = rua + " - " + bairro;
        this.morador = morador;
        this.dataHora = LocalDateTime.now();
    }

    public String getUrgencia() { return urgencia; }
    public String getTitulo()   { return titulo; }
    public String getDescricao(){ return descricao; }
    public String getBairro()   { return bairro; }
    public String getRua()      { return rua; }
    public Morador getMorador() { return morador; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getLocal()    { return local; }
    public int getId()          { return id; }

    // Setters para edição
    public void setTitulo(String titulo)       { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setUrgencia(String urgencia)   { this.urgencia = urgencia; }
    public void setBairro(String bairro)       { this.bairro = bairro; this.local = this.rua + " - " + bairro; }
    public void setRua(String rua)             { this.rua = rua;         this.local = rua + " - " + this.bairro; }

    public void exibirAlerta() {
        String emoji;
        String label;

        switch (urgencia.toUpperCase()) {
            case "VERMELHO" -> { emoji = "🔴"; label = "URGENTE"; }
            case "AMARELO"  -> { emoji = "🟡"; label = "ATENÇÃO"; }
            default         -> { emoji = "🟢"; label = "INFORMATIVO"; }
        }

        String hora = dataHora.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
        String data;
        java.time.LocalDate hoje = java.time.LocalDate.now();
        if (dataHora.toLocalDate().equals(hoje)) {
            data = "Hoje " + hora;
        } else {
            data = dataHora.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }

        System.out.println(emoji + " " + label + " - " + data);
        System.out.println(titulo);
        System.out.println(descricao);
        System.out.println("Bairro: " + bairro);
        System.out.println("Rua: " + rua);
        System.out.println("Enviado por: " + (morador != null ? morador.getNome() : "Desconhecido"));
    }
}
