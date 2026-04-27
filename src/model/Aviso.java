package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Aviso {
    protected int id;
    protected String titulo;
    protected String descricao;
    protected String urgencia;
    protected LocalDateTime dataHora;
    protected String local;
    protected Morador morador;
    protected List<ConfirmacaoVisualizacao> confirmacoes = new ArrayList<>();

    public Aviso(int id, String titulo, String descricao, String urgencia, String local, Morador morador) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.urgencia = urgencia;
        this.local = local;
        this.morador = morador;
        this.dataHora = LocalDateTime.now();
    }

    public String getUrgencia() { return urgencia; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }

    public abstract void exibirAlerta();
     public Morador getMorador() {
        return morador;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getLocal() {
        return local;
    }

    public int getId() {
        return id;
    }

    
}
