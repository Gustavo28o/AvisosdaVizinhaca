package service;

import model.*;
import java.util.ArrayList;
import java.util.List;


public class AvisoService {
    private List<Aviso> avisos = new ArrayList<>();
    private int contador = 1;

    public void enviarAviso(Aviso aviso) {
        avisos.add(aviso);
    }

    public List<Aviso> listar() {
        return avisos;
    }

    public List<Aviso> filtrarUrgencia(String urgencia) {
        List<Aviso> lista = new ArrayList<>();
        for (Aviso a : avisos) {
            if (a.getUrgencia().equalsIgnoreCase(urgencia)) {
                lista.add(a);
            }
        }
        return lista;
    }
    public int gerarProximoId() {
        return contador++;
    }
 // Sobrecarga de métodos para criar avisos de diferentes formas
    
    public void criarAviso(String mensagem) {
    System.out.println("Aviso geral: " + mensagem);
}

public void criarAviso(String mensagem, String rua) {
    System.out.println("Aviso para a rua " + rua + ": " + mensagem);
}

public void criarAviso(String mensagem, String rua, boolean urgente) {

    if (urgente) {
        System.out.println("AVISO URGENTE para " + rua + ": " + mensagem);
    } else {
        System.out.println("Aviso para " + rua + ": " + mensagem);
    }
}
}
   
