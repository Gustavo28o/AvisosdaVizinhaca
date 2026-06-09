package service;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return avisos.stream()
                .filter(a -> a.getUrgencia().equalsIgnoreCase(urgencia))
                .collect(Collectors.toList());
    }

    public List<Aviso> filtrarPorBairro(String bairro) {
        return avisos.stream()
                .filter(a -> a.getBairro() != null &&
                             a.getBairro().equalsIgnoreCase(bairro))
                .collect(Collectors.toList());
    }

    public List<String> listarBairros() {
        return avisos.stream()
                .map(Aviso::getBairro)
                .filter(b -> b != null && !b.isBlank())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public int gerarProximoId() {
        return contador++;
    }

    public boolean excluirAviso(int avisoId, int moradorId) {
        return avisos.removeIf(a ->
                a.getId() == avisoId
                && a.getMorador() != null
                && a.getMorador().getId() == moradorId);
    }

    public Aviso buscarPorId(int avisoId) {
        return avisos.stream()
                .filter(a -> a.getId() == avisoId)
                .findFirst()
                .orElse(null);
    }

    public List<Aviso> listarPorMorador(int moradorId) {
        return avisos.stream()
                .filter(a -> a.getMorador() != null && a.getMorador().getId() == moradorId)
                .collect(Collectors.toList());
    }
}
