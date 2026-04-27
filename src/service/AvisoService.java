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
}
    // resto do código...

